package com.encore.board.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")                         //홈
    public String home(){
        return "home";
    }

}
