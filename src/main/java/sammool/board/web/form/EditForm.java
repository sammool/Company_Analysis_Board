package sammool.board.web.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class EditForm {

    private String title;
    private String stockName;
    private String content;
    private MultipartFile multipartFile;
    private LocalDate updatedAt = LocalDate.now();
}
