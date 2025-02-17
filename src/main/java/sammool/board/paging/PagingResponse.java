package sammool.board.paging;

import lombok.Getter;

import java.util.List;
import java.util.ArrayList;

@Getter
public class PagingResponse<T> {

    private List<T> list = new ArrayList<>();
    private Pagination pagination;

    public PagingResponse(List<T> list, Pagination pagination){
        this.list.addAll(list);
        this.pagination = pagination;
    }
}
