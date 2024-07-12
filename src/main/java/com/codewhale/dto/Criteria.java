package com.codewhale.dto;

public class Criteria {
	private int amount = 10; // 페이지당 출력될 글 개수
	private int pageNum = 1; // 유저가 클릭한 페이지 번호가 저장될 변수 (현재 보여지는 페이지. 게시판 시작할때는 첫 페이지가 보여야하기 때문에 기본값은 1)
	private int startNum; // 초기값 없음. 유저가 클릭한 페이지에서 시작할 글의 번호 (row Number)
	public Criteria() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Criteria(int amount, int pageNum, int startNum) {
		super();
		this.amount = amount;
		this.pageNum = pageNum;
		this.startNum = startNum;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
}