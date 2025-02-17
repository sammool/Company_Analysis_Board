package sammool.board.web.form;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentForm {
    private String content;
    private String writer;
    private Long reportId;
    private LocalDate createdAt = LocalDate.now();
    private LocalDate updatedAt = LocalDate.now();
}

//format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));