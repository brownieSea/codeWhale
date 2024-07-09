package com.codewhale.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.codewhale.dao.BoardDao;
import com.codewhale.dao.MemberDao;
import com.codewhale.dto.BoardDto;
import com.codewhale.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	@Autowired
	private SqlSession sqlSession;
	
	@GetMapping(value = "/list")
	public String boardList(Model model) {
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		
		ArrayList<BoardDto> bDtos = boardDao.boardListDao();
		model.addAttribute("bDtos", bDtos);
		return "boardList";
	}
	
	@GetMapping(value = "/write")
	public String write(HttpSession session, HttpServletResponse response, Model model) {
		
		String sid = (String) session.getAttribute("sessionId");
		// 현재 로그인한 회원의 아이디
		
		if(sid == null) { // 참이면 로그인하지 않은 회원이 글쓰기 버튼을 클릭한 경우

			// 컨트롤러에서 경고창 띄우기
			try {
				response.setContentType("text/html;charset=utf-8"); // 경고창 텍스트를 utf-8로 변환
				response.setCharacterEncoding("utf-8");
				PrintWriter printWriter = response.getWriter();
				printWriter.println("<script>alert('"+"글쓰기는 회원만 가능합니다"+"');location.href='"+"login"+"';</script>");
				printWriter.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else { //로그인한 회원이 글쓰기 버튼을 클릭한 경우
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
	public String viewContent(HttpServletRequest request, Model model) {
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		BoardDto bDto = boardDao.viewContent(request.getParameter("bnum"));
		
		MemberDto mDto = memberDao.getMemberInfoDao(bDto.getBid());
		
		model.addAttribute("bDto", bDto);
		model.addAttribute("mDto", mDto);
		
		return "viewContent";
	}
	
	@GetMapping(value = "/contentModify")
	public String modifyContent(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) {
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		String sid = (String) session.getAttribute("sessionId");
		// 현재 로그인한 회원의 아이디
		
		BoardDto bDto = boardDao.viewContent(request.getParameter("bnum"));
		
		if (sid.equals(bDto.getBid()) || (sid.equals("admin"))) {//참이면 글을 쓴 회원과 현재 로그인 중인 아이디가 일치->수정,삭제 가능
			
			MemberDto mDto = memberDao.getMemberInfoDao(bDto.getBid());
			model.addAttribute("bDto", bDto);
			model.addAttribute("mDto", mDto);
			
		} else { // 글을 쓴 회원과 현재 로그인한 아이디가 다르므로 수정 삭제권한 없음
			
			// 컨트롤러에서 경고창 띄우기
			try {
				response.setContentType("text/html;charset=utf-8"); // 경고창 텍스트를 utf-8로 변환
				response.setCharacterEncoding("utf-8");
				PrintWriter printWriter = response.getWriter();
				printWriter.println("<script>alert('"+"글 수정 권한이 없습니다."+"');history.go(-1);</script>");
				printWriter.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
	public String contentDelete(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) {
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		String sid = (String) session.getAttribute("sessionId"); // 현재 로그인한 회원의 아이디
		
		BoardDto bDto = boardDao.viewContent(request.getParameter("bnum"));
		
		if (sid.equals(bDto.getBid()) || (sid.equals("admin"))) {//참이면 글을 쓴 회원과 현재 로그인 중인 아이디가 일치->수정,삭제 가능
			
			boardDao.contentDeleteDao(request.getParameter("bnum"));
			
		} else { // 글을 쓴 회원과 현재 로그인한 아이디가 다르므로 수정 삭제권한 없음
			
			// 컨트롤러에서 경고창 띄우기
			try {
				response.setContentType("text/html;charset=utf-8"); // 경고창 텍스트를 utf-8로 변환
				response.setCharacterEncoding("utf-8");
				PrintWriter printWriter = response.getWriter();
				printWriter.println("<script>alert('"+"글 삭제 권한이 없습니다."+"');history.go(-1);</script>");
				printWriter.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}		
		return "redirect:list";
	}
	
}
