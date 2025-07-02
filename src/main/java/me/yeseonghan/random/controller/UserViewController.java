package me.yeseonghan.random.controller;

import lombok.RequiredArgsConstructor;
import me.yeseonghan.random.domain.User;
import me.yeseonghan.random.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class UserViewController {

    private final UserService userService;

    @GetMapping("/")
    public String idx(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model, Principal principal){
        User user = userService.findByUserEmail(principal.getName());
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/login")
    public String login(){ return "login";}

    @GetMapping("/signup")
    public String signup(){ return "signup";}

    @GetMapping("/index_fake")  //비회원 입장용으로 만든건데 그냥 토큰 발행 입장으로 나중에 바꾸자
    public String index_fake(){ return "index";}

    @GetMapping("/map")
    public String map(Model model, Principal principal){
        User user = userService.findByUserEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("title", "DoobooLAB | DoobooMap");
        return "map";
    }
}
