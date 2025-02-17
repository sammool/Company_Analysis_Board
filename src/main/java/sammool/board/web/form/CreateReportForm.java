package sammool.board.web.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateReportForm {
    private String title;
    private String content;
    private String stockName;
    private MultipartFile multipartFile;
}
