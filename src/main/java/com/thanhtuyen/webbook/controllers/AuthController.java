package com.thanhtuyen.webbook.controllers;

import com.thanhtuyen.webbook.dto.UserDTO;
import com.thanhtuyen.webbook.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        UserDTO userDTO = new UserDTO();


        model.addAttribute("userRegister", userDTO);
        return "register";
    }

    @PostMapping("/do-register")
    public String register(@ModelAttribute("userRegister") UserDTO userDTO){
        userService.register(userDTO);
        return "redirect:/register";
    }
}
