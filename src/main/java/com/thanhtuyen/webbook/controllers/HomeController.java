package com.thanhtuyen.webbook.controllers;

import com.thanhtuyen.webbook.constraints.WebBook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/dashboard"})
    public String home(Model model){

        model.addAttribute(WebBook.TITLE, "Dashboard");
        return "dashboard";
    }
}
