package sammool.board.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class SearchDto {
    private int page; //현재 페이지 번호
    private int recordSize;
    private int pageSize;
    private Pagination pagination;

    private String keyword;
    private String searchType;


    public SearchDto(){
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
    }

    public int getOffset(){
        return (page-1) * recordSize;
    }
}
