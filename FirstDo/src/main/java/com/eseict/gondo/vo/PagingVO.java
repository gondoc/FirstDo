package com.eseict.gondo.vo;

import java.util.List;

public class PagingVO<T> {
    // 4개는 넘겨받고
    private int currentPage; // 현재 페이지
    private int pageSize;	 // 한 페이지당 글 수
    private int blockSize;	 // 페이지 리스트 개수
    private int totalCount;	 // 전체 글 개수

    // 5개는 계산하고
    private int totalPage;	 // 전체 페이지 수
    private int startNo;	 // 시작 글 번호
    private int endNo;		 // 끝   글 번호
    private int startPage;	 // 시작 페이지 번호
    private int endPage;	 // 끝   페이지 번호

    // 글이 저장될 리스트
    private List<T> list;

    // 4개는 생성자로 넘겨받자
    public PagingVO(int currentPage, int pageSize, int blockSize, int totalCount) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.blockSize = blockSize;
        this.totalCount = totalCount;
        // 나머지 필드는 계산해서 넣자
        calc();
    }

    private void calc() {
        // 넘어온 4개 값을 검증하자.
        if(currentPage<=0) currentPage=1;
        if(pageSize<2) pageSize=10;
        if(blockSize<2) blockSize=10;
        // 전체 개수는 DB에서 알아내는 값이므로 검증할 필요가 없다.
        // 글이 존재할때만 계산하자
        if(totalCount>0) {
            // 전체 페이지 수 = (전체개수-1)/페이지당글수 + 1
            totalPage = (totalCount-1)/pageSize + 1;
            // 현재 페이지가 전체 페이지 수를 넘을 수 없다.
            if(currentPage>totalPage) currentPage=1;
            // 시작 글 번호 0, 10, 20, 30, ...
            // 시작 글 번호 = (현재페이지-1)*페이지사이즈
            // 오라클의 경우는 인덱스값이 1부터 이므로 +1 을 해줘야 한다. // 마리아라서 0부터 한다.
            startNo = (currentPage-1)*pageSize;
            // 끝 글번호 = 시작 번호 + 페이지당 글 수 - 1;
            endNo = startNo + pageSize -1;
            // 끝 글번호가 전체 개수를 넘을 수 없다.
            // 오라클의 경우는 >= 가 아니고 > 이고 -1 을 생략해야 한다. // 인덱스가 1부터이기 때문에
            if(endNo>=totalCount) endNo = totalCount -1;
            // 시작 페이지 번호 = (현재페이지번호-1)/페이지당 개수 * 페이지당 개수 +1 // -1 하는 이유는 배수일때가 문제라서 -1을 해준다.
            startPage = (currentPage-1)/pageSize * pageSize +1;
            // 끝 페이지 번호 = 시작 페이지 번호 + 페이지 수 -1
            endPage = startPage + blockSize -1;
            // 끝 페이지 번호는 전체 페이지 번호를 넘을 수 없다.
            if(endPage>totalPage) endPage = totalPage;
        }
    }

    // Getter & Setter를 만들자 -- list 를 제외한 모든 변수는 밖에서 변경하지 않게끔 한다.
    // 즉, Getter만 만들어 준다.
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getStartNo() {
        return startNo;
    }

    public int getEndNo() {
        return endNo;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    @Override
    public String toString() {
        return "PagingVO [currentPage=" + currentPage + ", pageSize=" + pageSize + ", blockSize=" + blockSize
                + ", totalCount=" + totalCount + ", totalPage=" + totalPage + ", startNo=" + startNo + ", endNo="
                + endNo + ", startPage=" + startPage + ", endPage=" + endPage + "]";
    }

    // 2개의 메서드를 추가하자
    // 페이지 상단에 표시할 메서드를 추가하자
    public String getPageInfo() {
        String result = "전체 : " + totalCount + "개";
        if(totalCount>0) result += "(" + currentPage + "/" + totalPage + "Page)";
        return result;
    }
    // 페이지 하단에 표시할 메서드를 추가하자!!!
    public String getPageList() {
		/*
		<nav aria-label='Page navigation example'>
		  <ul class='pagination'>
		    <li class='page-item'>
		      <a class='page-link' href='#' aria-label="Previous">
		        <span aria-hidden='true'>&laquo;</span>
		      </a>
		    </li>
		    <li class='page-item'><a class='page-link' href='#'>1</a></li>
		    <li class='page-item'><a class='page-link' href='#'>2</a></li>
		    <li class='page-item'><a class='page-link' href='#'>3</a></li>
		    <li class='page-item'>
		      <a class='page-link' href='#' aria-label='Next'>
		        <span aria-hidden='true'>&raquo;</span>
		      </a>
		    </li>
		  </ul>
		</nav>
		 */
        StringBuffer sb = new StringBuffer();
        //sb.append("<nav aria-label='Page navigation example'>");
        sb.append("<ul class='pagination pagination-sm justify-content-center'>");
        // "이전"은 시작페이지 번호가 1보다 크다면 있다.
        if(startPage>1) {
            sb.append("<li class='page-item'>");
            sb.append("<a class='page-link' href='?p=" +(startPage-1)+ "&s="+pageSize+"&b="+blockSize+"' aria-label='Previous'>");
            sb.append("<span aria-hidden='true'>&laquo;</span>");
            sb.append("</a>");
            sb.append("</li>");
        }
        // 페이지 번호들을 출력해준다.
        for(int i=startPage;i<=endPage;i++) {
            if(i!=currentPage) { // 현재 페이지가 아니면 링크를 걸고
                sb.append("<li class='page-item'><a class='page-link' href='?p=" +(i)+ "&s=" + pageSize + "&b=" + blockSize + "'>" + i + "</a></li>");
            }else { // 현재 페이지는 링크를 걸지 않는다.
                sb.append("<li class='page-item active' aria-current='page'><a class='page-link' href='#'>" + i + "</a></li>");
            }
        }
        // "다음"은 끝 페이지번호가 전체 페이지 번호보다 적다면 있다.
        if(endPage<totalPage) {
            sb.append("<li class='page-item'>");
            sb.append("<a class='page-link' href='?p=" +(endPage+1)+ "&s="+pageSize+"&b="+blockSize+"' aria-label='Previous'>");
            sb.append("<span aria-hidden='true'>&raquo;</span>");
            sb.append("</a>");
            sb.append("</li>");
        }
        sb.append("</ul>");
        //sb.append("</nav>");

        return sb.toString();
    }
}
