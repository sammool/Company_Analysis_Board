package sammool.board.domain;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String nickname;
    private String role;

}
