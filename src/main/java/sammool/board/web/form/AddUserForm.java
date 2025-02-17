package sammool.board.web.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddUserForm {
    private String username;
    private String email;
    private String password;
    private String nickname;
}
