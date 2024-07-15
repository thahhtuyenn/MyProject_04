package com.thanhtuyen.webbook.controllers;

import com.thanhtuyen.webbook.constraints.WebBook;
import com.thanhtuyen.webbook.dto.AuthorDTO;
import com.thanhtuyen.webbook.dto.BookDTO;
import com.thanhtuyen.webbook.entities.Author;
import com.thanhtuyen.webbook.services.AuthorService;
import com.thanhtuyen.webbook.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/books")
@Slf4j
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @GetMapping("")
    public String index(Model model, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "message", required = false) String message){
        List<BookDTO> books =bookService.findAll();

        if (type != null && message != null){
            model.addAttribute("type", type);
            model.addAttribute("message", message);
        }

        log.info("Books: {}", books);
        model.addAttribute(WebBook.TITLE, "Book");
        model.addAttribute(WebBook.BOOKS, books);
        return "book";
    }

    @GetMapping("/disable")
    public String disable(Model model, @RequestParam("bookId") Long id){

        boolean status = bookService.disable(id);

        String type = status ? "success" : "danger";
        String message = status ? "Disable success" : "Disable failed";

        return String.format("redirect:/books?type=%s&message=%s", type, message);
    }

    @GetMapping("/enable")
    public String enable(Model model, @RequestParam("bookId") Long bookId){

        boolean status = bookService.disable(bookId);

        String type = status ? "success" : "danger";
        String message = status ? "Enable success" : "Enable failed";

        return String.format("redirect:/books?type=%s&message=%s", type, message);
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(value = "bookId", required = false) Long id){
        BookDTO bookDTO = new BookDTO();
        // Vấn đề ở ddaay bi
        List<Author> authors = authorService.getList();
        String title = "";
        if (id == null){
            title = "Add book";
            authors = authorService.findAllByActive(true);
        }else {
            bookDTO = bookService.findById(id);
            title = "Update book";
        }

        log.info("Authors {}", authors);
        log.info("Books {}", bookDTO);
        model.addAttribute(WebBook.BOOK, bookDTO);
        model.addAttribute(WebBook.TITLE, title);
        model.addAttribute(WebBook.AUTHORS, authors);

        return "editBook";
    }

    @PostMapping("/do-edit")
    public String edit(Model model, @ModelAttribute(WebBook.BOOK)BookDTO bookDTO, @RequestParam("imageProduct")MultipartFile imageProduct){
        String message = "";
        String type = "danger";
        BookDTO bookDTONew = bookService.save(bookDTO, imageProduct);
        if (bookDTONew != null){
            type = "success";
            if (bookDTONew.getId() != null){
                message = "Update book success";
            }else {
                message = "Add book success";
            }
        }else {
            if (bookDTONew.getId() != null){
                message = "Update book fail";
            }else {
                message = "Add book fail";
            }
        }

        model.addAttribute("type", type);
        model.addAttribute("message", message);

        return String.format("redirect:/books?type=%s&message=%s", type, message);
    }


}
