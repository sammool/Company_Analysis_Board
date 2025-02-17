package sammool.board.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Comment {
    private Long id;
    private String content;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;

    private Long reportId; //N:1
    private String writer;
}
