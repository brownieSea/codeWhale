package com.codewhale.dto;

public class PageDto {

	private int startPage;  // 하단 페이지 네이게이션에 표시될 페이지 번호의 시작번호
	private int endPage; // 하단 페이지 네이게이션에 표시될 페이지 번호의 마지막번호
	private boolean next; // 현재 페이지 네비게이션 이상으로 페이지가 더 있는지의 여부
	private boolean prev; // 현재 페이지 네비게이션 이전에 페이지가 더 있는지의 여부
	private int total;  // 총 글의 개수
	private int realEndPage;  // 실제 마지막 페이지
	
	private Criteria criteria; // Criteria Dto 의 변수 값들을 불러오기 위핸 객체 선언. 멤버 변수

	public PageDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageDto(int startPage, int endPage, boolean next, boolean prev, int total, Criteria criteria) {
		super();
		this.startPage = startPage;
		this.endPage = endPage;
		this.next = next;
		this.prev = prev;
		this.total = total;
		this.criteria = criteria;
	}

	
	// 총 글의 개수와 Criteria 의 값만 가져오는 생성자가 필요.
	public PageDto(int total, Criteria criteria) {
		
		this.total = total;
		this.criteria = criteria;
		
		this.endPage = (int) ((Math.ceil(criteria.getPageNum()/10.0))*10) ; //Math.ceil -> 올림
		// 위 식으로 페이지 값이 1~10 사이의 수가 들어오면 endPage의 값이 10으로 세팅
		// 위 식으로 페이지 값이 11~20 사이의 수가 들어오면 endPage의 값이 20으로 세팅
		
		this.startPage = this.endPage - 9 ;
		
		// 총 128개의 글이 존재한다고 가정할 때, 
		//       1 2 3 4 5 6 7 8 9 10 next
		// prev 11 12 13
		// 과 같은 모양으로 하단 페이지 네비게이션이 설정되어야 함.
		
		// 실체 총 글 수로 계산한 마지막 페이지 번호 -> 총 글의 개수가 128개라고 가정할 때 마지막 페이지는 13 페이지로 계산.
		this.realEndPage = (int) Math.ceil(total*1.0 / criteria.getAmount()) ;
		
		if (this.realEndPage < this.endPage) {  // 실제 마지막 페이지 값(realEndpage)이 계산한 endpage 수보다 작을 때 실제 페이지 값으로 교체
			this.endPage = this.realEndPage;	
		}
		
		this.prev = this.startPage != 1;  // startPage 값이 1이 아니면 prev는 true 
		this.next = this.realEndPage > this.endPage;  // endPage 번호가 실제 페이지 번호보다 작은 경우에만 next가 true
		
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRealEndPage() {
		return realEndPage;
	}

	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}


}
