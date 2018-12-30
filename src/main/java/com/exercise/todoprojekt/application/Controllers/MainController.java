package com.exercise.todoprojekt.application.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainpage(){
        return "redirect:/todo";
    }
}