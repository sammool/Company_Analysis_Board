package sammool.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sammool.board.domain.User;
import sammool.board.mapper.UserMapper;
import sammool.board.web.form.AddUserForm;
import sammool.board.web.form.LoginForm;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public void save(AddUserForm addUserForm){
        User user = new User();
        user.setUsername(addUserForm.getUsername());
        user.setEmail(addUserForm.getEmail());
        user.setPassword(addUserForm.getPassword());
        user.setNickname(addUserForm.getNickname());
        userMapper.save(user);
    }

    public User getByEmail(String email){
        return userMapper.getByEmail(email).orElse(null);
    }

    public User login(LoginForm form){

        return userMapper.getByEmail(form.getEmail())
                .filter(m -> m.getPassword().equals(form.getPassword()))
                .orElse(null);

    }

}
