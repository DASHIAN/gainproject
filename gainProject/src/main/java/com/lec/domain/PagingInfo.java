package com.lec.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingInfo {
	private int curPage = 1; // 현재페이지 번호
	private int rowSizePerPage = 10; // 한 페이지당 레코드 수 기본 10
	private int pageSize = 10; // 페이지리스트에서 보여줄 페이지 갯수(기본 10page)
	private int totalRowCount; // 총 레코드 수
	private int firstRow; // 시작 레코드 번호
	private int lastRow; // 마지막 레코드 번호
	private int totalPageCount; // 총페이지 수
	private int startPage; // 페이지리스트에서 시작페이지번호
	private int endPage; // 페이지리스트에서 마지막페이지번호
	private String searchType; // 검색유형(제목, 내용...)
	private String searchWord; //검색단어
	
	// page계산
	public void pageSetting() {
		totalPageCount = (totalRowCount-1) / rowSizePerPage + 1;
		firstRow = (curPage-1) * rowSizePerPage;
		lastRow = firstRow + rowSizePerPage;
		if(lastRow > totalRowCount) lastRow = totalRowCount;
		startPage = (curPage-1) / pageSize + pageSize +1;
		endPage = startPage + pageSize - 1;
		if(endPage > totalPageCount) endPage = totalPageCount;
	}
}








