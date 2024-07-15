package com.thanhtuyen.webbook.controllers;

import com.thanhtuyen.webbook.dto.RoleDTO;
import com.thanhtuyen.webbook.dto.UserDTO;
import com.thanhtuyen.webbook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserHomePage {
    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String home(Principal principal){

        if (principal.getName() != null){
            UserDTO userDTO = userService.findByUsername(principal.getName());
            for (RoleDTO roleDTO : userDTO.getRoles()){
                if (roleDTO.getName().equals("ADMIN")){
                    return "redirect:/admin/dashboard";
                }
            }
        }
        return "home";
    }
}
