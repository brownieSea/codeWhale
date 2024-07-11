package com.codewhale.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codewhale.dao.MemberDao;
import com.codewhale.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	private SqlSession sqlSession;
	
	@GetMapping(value = "/")
	public String home() {
		return "index";
	}

	@GetMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@GetMapping(value = "/join")
	public String join() {
		
		return "join";
	}
	
	@PostMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request, Model model) {
		
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		int idCheck = memberDao.idCheckDao(request.getParameter("mid"));
		// idcheck == 1이면 가입불가, 0이면 가입가능 (아이디가 이미 가입되어 있으면 1 반환, 없으면 0 반환)
		
		if (idCheck == 1) {  // 참이면 가입불가
			model.addAttribute("joinFail", 1);
		} else {
			memberDao.joinDao(request.getParameter("mid"), request.getParameter("mpw"), request.getParameter("mname"), request.getParameter("memail"));
			model.addAttribute("mid", request.getParameter("mid"));
			model.addAttribute("mname", request.getParameter("mname"));
		}
		return "joinOk";
	}
	
	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@PostMapping(value = "loginOk")
	public String loginOk(HttpServletRequest request, Model model, HttpSession session) {
		
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		int loginCheck = memberDao.loginDao(request.getParameter("mid"), request.getParameter("mpw"));
		// 1이면 로그인 성공, 0이면 로그인 실패
		
		MemberDto memberDto = null;

		if (loginCheck != 1) {  // 참이면 로그인 실패
			model.addAttribute("loginFail", 1);
		} else {
			// 로그인 성공 -> 세션에 현재 로그인 성공된 아이디를 저장
			memberDto = memberDao.getMemberInfoDao(request.getParameter("mid"));
			session.setAttribute("sessionId", request.getParameter("mid"));
			session.setAttribute("sessionName", memberDto.getMname());
		}		
		return "loginOk";
	}
	
	@GetMapping(value = "logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		
		// 컨트롤러에서 경고창 띄우기
		try {
			response.setContentType("text/html;charset=utf-8"); // 경고창 텍스트를 utf-8로 변환
			response.setCharacterEncoding("utf-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.println("<script>alert('로그아웃 하시겠습니까'); location.href='login';</script>");

			printWriter.flush();
			session.invalidate(); // 로그아웃 -> 세션 삭제

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "login";
	}
	
	@GetMapping(value = "modify")
	public String modify(HttpSession session, Model model, @ModelAttribute("sid") String sid) {
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		MemberDto memberDto = memberDao.getMemberInfoDao(sid); // 현재 로그인한 회원의 모든 정보
		model.addAttribute("mDto", memberDto);	
		return "modifyMember";
	}
	
	@PostMapping(value = "/modifyOk")
	public String modifyOk(HttpServletRequest request, Model model) {
		
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		memberDao.modifyInfoDao(request.getParameter("mid"), request.getParameter("mpw"), request.getParameter("mname"), request.getParameter("memail"));
		
		MemberDto memberDto = memberDao.getMemberInfoDao(request.getParameter("mid")); // 현재 로그인한 회원의 모든 정보
		
		model.addAttribute("mDto", memberDto);

		return "modifyOk";
	}
	
}
