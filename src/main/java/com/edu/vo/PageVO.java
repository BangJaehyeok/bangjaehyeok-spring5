package com.edu.vo;
/*
 * 이 클래스는 공통(회원관리, 게시물관리)으로 사용하는 페이징처리 + 검색기능의 클래스.
 * 이 클래스는 오라클이든, mysql이든 어디서든 공통으로사용하는 Get/Set입니다. 
 * 페이징에 사용되는 변수(쿼리변수+VO변수) 아래
 * queryStartNo, queryPerPageNum, page, perPageNum, startPage, endPage
 * 검색에 사용되는 변수(쿼리변수만) : 검색어(search_keyword), 검색조건(search_type)
*/
public class PageVO {
	private int queryStartNo;//쿼리전용변수, 페이징쿼리에서 시작페이지 인덱스번호표시 변수
	private int queryPerPageNum;//쿼리전용, 페이징쿼리에서 1페이지당 출력할 개수표시 변수
	private Integer page;//jsp에서 발생 선택한 페이지 번호변수. 자바전용. int인데 null값을 허용.
	private int perPageNum; //UI하단에 보여줄 페이징개수 계산에 필요
	private int totalCount; //계산식의 기초값으로 이 전체개수가 구해진 이후 계산식이 진행됨.
	private int startPage;//위 perPageNum으로 구하는 UI하단 페이지 시작번호
	private int endPage;//perPageNum으로 구하는 UI하단 페이지 끝번호
	private boolean prev;//UI하단 이전 페이지로 이동이 가능한지 판별하는 변수
	private boolean next;//UI하단 다음 페이지로 이동이 가능한지 판별하는 변수
	private String search_keyword;//jsp에서 받은 검색어 쿼리로 보낼예정
	private String search_type;//검색조건에 해당하는 쿼리전용 변수
	
	public int getQueryStartNo() {
		//this.page-1을 하는 이유 : jsp에서는 1,2,3..이렇게 받지만,
		//쿼리에서는 0,1,2,3..으로 사용되기때문에 
		queryStartNo = (this.page-1)*queryPerPageNum;
		return queryStartNo;
	}
	public void setQueryStartNo(int queryStartNo) {
		this.queryStartNo = queryStartNo;
	}
	public int getQueryPerPageNum() {
		return queryPerPageNum;
	}
	public void setQueryPerPageNum(int queryPerPageNum) {
		this.queryPerPageNum = queryPerPageNum;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		//전체개수 값을 지정한 이후 계산식 실행
		calcPage();
	}
	private void calcPage() {
		// 이 메서드는 totalCount변수값을 기반으로 prev,next,startPage,endPage 등을 구현하게 됩니다.
		//ceil은 소수점을 모두 올림해주는 함수이다. 1.1=>2 , 2.3=>3(아래)
		//ceil(11/10)*10 => 20페이지. tempEnd 1-10페이지에서 11페이지값이 존재하면, 홈페이지에 임시로 20이라는 숫자를 줍니다.
		int tempEnd = (int) Math.ceil(page/(double)this.perPageNum)*this.perPageNum;
	//jsp에서 클릭한 페이지번호 예로 1부터 10까지는 클릭하면, 임시 끝페이지(tempEnd)가 10이 주어진다.
	//만약 11페이지를 클릭하면, 임시 끝페이지가 20이 된다. tempEnd변수값으로 아래내용에 이용해서 시작페이지 값을 계산하게된다.
	this.startPage = (tempEnd - this.perPageNum) +1;
	//UI페이지 하단에 페이지번호가 반복되게 나오는게 하는데 필요한 변수
	//예, 1-10까지는 page를 jsp에서 클릭했을때 시작페이지가 1페이지이다. but, 11~20페이지까지는 위 계산식을 이용하면 시작페이지가 항상 11페이지로 된다.
	//위 startPage변수 jsp에서 반복문의 시작 값으로 사용.
	//지금 토탈개수는 101개 이상
	if(tempEnd*this.queryPerPageNum > this.totalCount) {
		this.endPage = (int) Math.ceil((this.totalCount/(double)this.queryPerPageNum));
		//위 계산식을 예를들면, 토탈페이지(101)/현재페이지(10) = ceil(10.1) = 11(endpage)
		} else {
			this.endPage = tempEnd;//20(endpage)
		}
	
	//여기까지가 startPage, endPage구하는 계산식
	// 이후는 prev, next 구하는 계산식
	// UI하단의 페이지번호 상상 <(비활성) 1 2 3 4 5 6 7 8 9 10 >(활성-링크값 10+1)
		this.prev = (this.startPage !=1); //startPage가 1페이지가 아닐때만 prev비활성화=false
		this.next = (this.endPage*this.queryPerPageNum) < this.totalCount;
		//10*10 =100 < 101이상이기 때문에 next가 활성화된다. = true
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
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public String getSearch_keyword() {
		return search_keyword;
	}
	public void setSearch_keyword(String search_keyword) {
		this.search_keyword = search_keyword;
	}
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	
}
