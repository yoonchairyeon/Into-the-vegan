package kr.ac.sku.intothevegan.presentation;


import kr.ac.sku.intothevegan.application.UserService;
import kr.ac.sku.intothevegan.application.dto.UserDto;
import kr.ac.sku.intothevegan.application.dto.UserSessionDto;
import kr.ac.sku.intothevegan.application.security.auth.LoginUser;
import kr.ac.sku.intothevegan.application.validator.CustomValidators;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

//회원 관련 Controller
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    private final CustomValidators.EmailValidator EmailValidator;
    private final CustomValidators.UsernameValidator UsernameValidator;

    /* 커스텀 유효성 검증을 위해 추가 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(EmailValidator);
        binder.addValidators(UsernameValidator);
    }

    @GetMapping("/auth/join")
    public String join() {
        return "/user/signup";
    }

    /* 회원가입 */
    @PostMapping("/auth/joinProc")
    public String join( UserDto userDto) {
        userService.Join(userDto);
        return "redirect:/auth/login";
    }

    @GetMapping("/auth/login")
    public String login(@RequestParam(value = "error", required = false)String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/user/login";
    }

    /* Security에서 로그아웃은 기본적으로 POST지만, GET으로 우회 */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    /* 회원정보 수정 */
    @GetMapping("/modify")
    public String modify(@LoginUser UserSessionDto user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "user/usermodify";
    }
}
