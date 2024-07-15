package com.thanhtuyen.webbook.controllers;

import com.thanhtuyen.webbook.constraints.WebBook;
import com.thanhtuyen.webbook.dto.CategoryDTO;
import com.thanhtuyen.webbook.entities.Category;
import com.thanhtuyen.webbook.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String index(Model model, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "message", required = false) String message){
        List<Category> categories = categoryService.getList();

        if (type != null && message != null){
            model.addAttribute("type", type);
            model.addAttribute("message", message);
        }
        model.addAttribute(WebBook.TITLE, "Manager Category");
        model.addAttribute(WebBook.CATEGORIES, categories);
        return "category";
    }

    @GetMapping("/disable")
    public String disable(@RequestParam("categoryId") Long categoryId, Model model){
        boolean status = categoryService.disable(categoryId);
        String type = status ? "success" : "danger";
        String message = status ? "Disable success" : "Disable failed";
        return String.format("redirect:/categories?type=%s&message=%s", type, message);
    }

    @GetMapping("/enable")
    public String enable(@RequestParam("categoryId") Long categoryId, Model model){
       boolean status = categoryService.enable(categoryId);
        String type = status ? "success" : "danger";
        String message = status ? "Enable success" : "Enable failed";
        return String.format("redirect:/categories?type=%s&message=%s", type, message);
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(value = "categoryId", required = false) Long id, Model model){
        CategoryDTO categoryDTO = new CategoryDTO();

        String title = "";
        if (id == null){
            //them moi
            title = "Add Category";


        }else {
            //cap nhat
            categoryDTO = categoryService.getByCategoryId(id);
            title = "Update Category";
        }
        log.info("Category: {}", categoryDTO);
        model.addAttribute(WebBook.CATEGORY, categoryDTO);
        model.addAttribute(WebBook.TITLE, title);
        return "editCategory";
    }

    @PostMapping("/do-edit")
    public String doEdit(Model model, @ModelAttribute(name = WebBook.CATEGORY) CategoryDTO categoryDTO ){
        log.info("Catrgory: {}", categoryDTO);
        String message = "";
        String type = "danger";
        Category category = categoryService.save(categoryDTO);
        if (category != null){
            type = "success";
            if (category.getId() != null){
                message = "Update category success";
            }else {
                message = "Add category success";
            }
        }else {
            if (category.getId() != null){
                message = "Update category fail";
            }else {
                message = "Add category fail";
            }
        }

        model.addAttribute("type", type);
        model.addAttribute("message", message);

        return String.format("redirect:/categories?type=%s&message=%s", type, message);
    }
}
