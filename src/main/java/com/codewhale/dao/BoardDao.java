package com.codewhale.dao;

import java.util.ArrayList;

import com.codewhale.dto.BoardDto;
import com.codewhale.dto.MemberDto;

public interface BoardDao {
	public ArrayList<BoardDto> boardListDao(int amount, int pageNum); // 게시판 리스트 가져오기, 페이징을 위해서 한 페이지당 보여줄 글의 개수, 사용자가 클릭한 페이지의 번호를 인수로 받는다.
	public void writeDao(String bid, String bname, String btitle, String bcontent); //글쓰기
	public BoardDto viewContent(String bnum); //게시판 글 보기
	public void countHitDao(String bnum); // hit 수 증가
	public void modifyContentDao(String bnum, String btitle, String bcontent); //게시판 글 수정
	public void contentDeleteDao(String bnum);//글 삭제
	public int boardTotalCountDao();  // 게시판에 저장된 모든 글의 갯수
	public ArrayList<BoardDto> searchKeyDao(int amount, int pageNum, String searchKey); // 게시판 제목 또는 내용 키워드 검색. 페이징을 위한 amount, pageNum 과 검색어를 인수로 받는다
	public int searchResultTotalDao(String searchKey); // 검색 결과 글 총 개수 카운트
}
