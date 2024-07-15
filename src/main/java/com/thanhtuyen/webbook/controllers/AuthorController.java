package com.thanhtuyen.webbook.controllers;

import com.thanhtuyen.webbook.constraints.WebBook;
import com.thanhtuyen.webbook.dto.AuthorDTO;
import com.thanhtuyen.webbook.entities.Author;
import com.thanhtuyen.webbook.mapper.AuthorMapper;
import com.thanhtuyen.webbook.services.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/authors")
@Slf4j
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("")
    public String index(Model model, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "message", required = false) String message){
        List<Author> authors = authorService.getList();
        model.addAttribute(WebBook.TITLE, "Author");
        model.addAttribute(WebBook.AUTHORS, authors);
        if (type != null && message != null){
            model.addAttribute("type", type);
            model.addAttribute("message", message);
        }

        return "author";
    }

    @GetMapping("/disable")
    public String disable(@RequestParam("authorId") Long id, Model model){
        boolean status = authorService.disable(id);

        String type = status ? "success" : "danger";
        String message = status ? "Disable success" : "Disable failed";

        return String.format("redirect:/authors?type=%s&message=%s", type, message);
    }

    @GetMapping("/enable")
    public String enable(@RequestParam("authorId") Long id, Model model){
        boolean status = authorService.enable(id);

        String type = status ? "success" : "danger";
        String message = status ? "Enable success" : "Enable failed";

        return String.format("redirect:/authors?type=%s&message=%s", type, message);
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(value = "authorId", required = false) Long id){
        AuthorDTO author = new AuthorDTO();
        String title = "";
        if (id == null){
            title = "Add author";
        }else {
            title = "Update author";
            author = AuthorMapper.toDto(authorService.getById(id));
        }
        log.info("Author: {}", author);
        model.addAttribute(WebBook.TITLE, title);
        model.addAttribute(WebBook.AUTHOR, author);

        return "editAuthor";
    }

    @PostMapping("/do-edit")
    public String doEdit(Model model, @ModelAttribute(name = WebBook.AUTHOR) AuthorDTO authorDTO){
        String type = "fail";
        String message = "";
        Author author = authorService.save(authorDTO);
        if (author != null){
            type = "success";
            if (author.getId() != null){
                message = "Update author success";
            }else {
                message = "Add author success";
            }
        }else {
            if (author.getId() == null){
                message = "Update author fail";
            }else {
                message = "Add author fail";
            }
        }

        model.addAttribute("type", type);
        model.addAttribute("message", message);
        return String.format("redirect:/authors?type=%s&message=%s", type, message);
    }


}
