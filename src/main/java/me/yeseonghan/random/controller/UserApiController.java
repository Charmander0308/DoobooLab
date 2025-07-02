package me.yeseonghan.random.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.yeseonghan.random.dto.AddUserRequest;
import me.yeseonghan.random.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserApiController {

    private final UserService userService;

    @GetMapping("/check_email")     //회원가입 시 이메일 중복검사
    public Map<String, Boolean> checkEmailDuplicate(@RequestParam String userEmail){
        return userService.checkEmailDuplicate(userEmail);
    }

    @GetMapping("/check_nickname")  //회원가입 시 닉네임 중복검사
    public Map<String, Boolean> checkNicknameDuplicate(@RequestParam String userNickname){
        return userService.checkNicknameDuplicate(userNickname);
    }

    @GetMapping("/logout")  //로그아웃 시
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    @PostMapping("/signup") //회원가입처리
    public String signup(@RequestBody AddUserRequest request){
//        System.out.println(request.toString());
        if (request.getUserPassword() == null || request.getUserPassword().isEmpty()) {
            return "redirect:/signup?error=password-required";
        }
        userService.save(request);
        return "redirect:/login";
    }
}
