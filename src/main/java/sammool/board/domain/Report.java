package sammool.board.domain;

import java.time.LocalDate;
import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Report {
    private Long id;
    private String title;
    private String content;
    private String stockName;
    private int hits;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;

    private User user;
    private Fileinfo fileinfo;
    private List<Comment> comments = new ArrayList<>();

}
