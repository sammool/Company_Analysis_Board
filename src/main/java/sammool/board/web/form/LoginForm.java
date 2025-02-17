package sammool.board.web.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginForm {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
