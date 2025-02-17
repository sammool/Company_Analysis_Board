package sammool.board.web.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sammool.board.domain.User;
import sammool.board.service.UserService;
import sammool.board.web.SessionConst;
import sammool.board.web.form.AddUserForm;
import sammool.board.web.form.LoginForm;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerForm(@ModelAttribute("form") AddUserForm form){
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("form") AddUserForm form){
        userService.save(form);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("form") LoginForm form){
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("form") LoginForm form, BindingResult bindReulst,
                        HttpServletRequest request){

        User loginUser = userService.login(form);

        if(loginUser==null){
            bindReulst.reject("loginFail","이메일과 비밀번호를 확인해주세요");
            return "user/login";
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);
        log.info("로그인 세션 정보:{}", session);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        log.info("로그아웃 되었습니다");
        return "redirect:/";
    }
}