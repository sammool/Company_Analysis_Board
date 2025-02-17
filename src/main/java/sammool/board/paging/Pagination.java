package sammool.board.paging;

import lombok.Getter;

@Getter
public class Pagination {

    private int totalRecordCoount;   // 전체 데이터 수
    private int totalPageCount;      // 전체 페이지 수
    private int startPage;          // 첫 페이지 번호
    private int endPage;            // 끝 페이지 번호
    private int limitStart;         // LIMIT 시작 위치
    private boolean existPrevPage;  // 이전 페이지 존재 여부
    private boolean existNextPage;  // 다음 페이지 존재 여부

    public Pagination(int totalRecordCoount, SearchDto params){
        if(totalRecordCoount > 0){
            this.totalRecordCoount = totalRecordCoount;
            calculation(params);
        }
    }

    private void calculation(SearchDto params){
        //전체 페이지 수 계산
        totalPageCount = ((totalRecordCoount - 1) / params.getRecordSize()) + 1;
        if(params.getPage() > totalPageCount){
            params.setPage(totalPageCount);
        }

        //첫 페이지 번호
        startPage = ((params.getPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;
        //끝 페이지 번호
        endPage = startPage + params.getPageSize() - 1;

        if(endPage > totalPageCount){
            endPage = totalPageCount;
        }

        //Limit시작 위치
        limitStart = (params.getPage() - 1) * params.getRecordSize();
        //이전 페이지 존재 여부
        existPrevPage = startPage !=1;
        //다음 페이지 존재 여부
        existNextPage = (endPage * params.getRecordSize()) < totalRecordCoount;
    }
}
