package com.codewhale.dao;

import com.codewhale.dto.MemberDto;

public interface MemberDao {
	public void joinDao(String mid, String mpw, String mname, String memail); //회원가입
	public int idCheckDao(String mid); //아이디 존재여부 반환
	public int loginDao(String mid, String mpw); //로그인
	public MemberDto getMemberInfoDao(String mid); //아이디로 검색해서 회원정보 가져오기
	public void modifyInfoDao(String mid, String mpw, String mname, String memail); //회원정보 수정
}
