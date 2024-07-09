package com.codewhale.dao;

import java.util.ArrayList;

import com.codewhale.dto.BoardDto;
import com.codewhale.dto.MemberDto;

public interface BoardDao {
	public ArrayList<BoardDto> boardListDao(); // 게시판 리스트 가져오기
	public void writeDao(String bid, String bname, String btitle, String bcontent); //글쓰기
	public BoardDto viewContent(String bnum); //게시판 글 보기
	public void modifyContentDao(String bnum, String btitle, String bcontent); //게시판 글 수정
	public void contentDeleteDao(String bnum);//글 삭제
}
