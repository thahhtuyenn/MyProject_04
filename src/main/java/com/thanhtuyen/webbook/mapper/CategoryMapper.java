package com.thanhtuyen.webbook.mapper;

import com.thanhtuyen.webbook.dto.CategoryDTO;
import com.thanhtuyen.webbook.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {
    public static Category toEntity(CategoryDTO categoryDTO){
        Category entity = new Category();
        if (categoryDTO.getId() != null){
            entity.setId(categoryDTO.getId());
        }
        entity.setName(categoryDTO.getName());
        entity.setDescription(categoryDTO.getDescription());
        return entity;
    }

    public static Category toEntity(Category categoryOld, CategoryDTO categoryDTO){
        categoryOld.setName(categoryDTO.getName());
        categoryOld.setDescription(categoryDTO.getDescription());
        return categoryOld;
    }

    public static CategoryDTO toDto(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.set_active(category.is_active());
        return categoryDTO;
    }

    public static List<CategoryDTO> toDtos(List<Category> categories){
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        categories.forEach(c -> {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(c.getId());
            categoryDTO.setName(c.getName());
            categoryDTO.setDescription(c.getDescription());
            categoryDTO.set_active(c.is_active());
            categoryDTOs.add(categoryDTO);
        });
        return categoryDTOs;
    }
}
