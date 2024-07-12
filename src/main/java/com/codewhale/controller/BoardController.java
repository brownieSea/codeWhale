package com.codewhale.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.codewhale.dao.BoardDao;
import com.codewhale.dao.MemberDao;
import com.codewhale.dto.BoardDto;
import com.codewhale.dto.Criteria;
import com.codewhale.dto.MemberDto;
import com.codewhale.dto.PageDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class BoardController {

	@Autowired
	private SqlSession sqlSession;
	
	@GetMapping(value = "/list")
	public String boardList(Model model, Criteria criteria, HttpServletRequest request) {
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		
		String currentPageNum = request.getParameter("pageNum");  // 사용자가 클릭한 게시판 페이지 번호
		
		if(currentPageNum != null) { // 게시판 메뉴를 클릭해서 게시판 목록이 보일 경우에는 pageNum값이 null 이므로 에러 발생하기에 체크 필요
			criteria.setPageNum(Integer.parseInt(currentPageNum));  // 사용자가 클릭한 페이지 번호를 Criterial 객체 내 변수인 pageNum 값으로 세팅
		}
		
		int total = boardDao.boardTotalCountDao(); // 게시판 내 모든 글의 총 개수
		
		PageDto pageDto = new PageDto(total, criteria);
		
		ArrayList<BoardDto> bDtos = boardDao.boardListDao(criteria.getAmount(), criteria.getPageNum());
		model.addAttribute("bDtos", bDtos);
		model.addAttribute("pageDto", pageDto);  // startPage, endPage, next, prev, total, criteria 모두 전달 

		return "boardList";
	}
	
	@GetMapping(value = "/write")
	public String write(HttpServletResponse response, Model model, @ModelAttribute("sid") String sid) {
		
		if(sid == null) { // 참이면 로그인하지 않은 회원이 글쓰기 버튼을 클릭한 경우

			// 컨트롤러에서 경고창 띄우기
			try {
				response.setContentType("text/html;charset=utf-8"); // 경고창 텍스트를 utf-8로 변환
				response.setCharacterEncoding("utf-8");
				PrintWriter printWriter = response.getWriter();
				printWriter.println("<script>alert('글쓰기는 회원만 가능합니다');location.href='login';</script>");
				printWriter.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
			MemberDto memberDto = memberDao.getMemberInfoDao(sid); // 현재 로그인한 회원의 모든 정보
			model.addAttribute("mDto", memberDto);
		}
		
		return "write";
	}
	
	@GetMapping(value = "/writeOk")
	public String writeOk(HttpServletRequest request, Model model) {
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		
		boardDao.writeDao(request.getParameter("bid"), request.getParameter("bname"), request.getParameter("btitle"), request.getParameter("bcontent"));
		
		return "redirect:list";
	}
	
	@GetMapping(value = "viewContent")
	public String viewContent(HttpServletRequest request, Model model, HttpSession session, @ModelAttribute("sid") String sid) {
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);

		boardDao.countHitDao(request.getParameter("bnum"));
		
		BoardDto bDto = boardDao.viewContent(request.getParameter("bnum"));
		
		MemberDto mDtoView = memberDao.getMemberInfoDao(bDto.getBid());
		
		model.addAttribute("bDto", bDto);
		model.addAttribute("mDtoView", mDtoView);  // 글작성자 memberDto
		
		return "viewContent";
	}
	
	@GetMapping(value = "/contentModify")
	public String modifyContent(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response, @ModelAttribute("sid") String sid) {
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		BoardDto bDto = boardDao.viewContent(request.getParameter("bnum"));

		if (sid == null || (!sid.equals(bDto.getBid()) && !sid.equals("admin"))) {  
		//if (sid == null || !sid.equals(bDto.getBid()) || !sid.equals("admin")) {  // 비로그인 상태이거나 글작성자와 아이디가 일치하지 않으면

			// 컨트롤러에서 경고창 띄우기
			try {
				response.setContentType("text/html;charset=utf-8"); // 경고창 텍스트를 utf-8로 변환
				response.setCharacterEncoding("utf-8");
				PrintWriter printWriter = response.getWriter();
				printWriter.println("<script>alert('글 수정 권한이 없습니다.');history.go(-1);</script>");
				printWriter.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			model.addAttribute("bDto", bDto);
		}
		
		return "contentModify";
	}
	
	@GetMapping(value = "/contentModifyOk")
	public String modifyContentOk(HttpServletRequest request) {
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		
		boardDao.modifyContentDao(request.getParameter("bnum"), request.getParameter("btitle"), request.getParameter("bcontent"));
		
		return "redirect:list";
	}
	
	@GetMapping(value = "/contentDelete")
	public String contentDelete(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response, @ModelAttribute("sid") String sid) {
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
	
		BoardDto bDto = boardDao.viewContent(request.getParameter("bnum"));

		if (sid == null || (!sid.equals(bDto.getBid()) && !sid.equals("admin"))) {
			// 컨트롤러에서 경고창 띄우기
			try {
				response.setContentType("text/html;charset=utf-8"); // 경고창 텍스트를 utf-8로 변환
				response.setCharacterEncoding("utf-8");
				PrintWriter printWriter = response.getWriter();
				printWriter.println("<script>alert('글 삭제 권한이 없습니다.');history.go(-1);</script>");
				printWriter.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			boardDao.contentDeleteDao(request.getParameter("bnum"));
		}

		return "redirect:list";
	}
	
}
