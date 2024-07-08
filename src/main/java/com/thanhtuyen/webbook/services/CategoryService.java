package com.thanhtuyen.webbook.services;

import com.thanhtuyen.webbook.dto.CategoryDTO;
import com.thanhtuyen.webbook.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    public List<Category> getList();
    public CategoryDTO getById(Long id);
    public Category save(CategoryDTO category);
    public boolean enable(Long id);
    public boolean disable(Long id);
    public List<Category> getByName(String name);

    CategoryDTO getByCategoryId(Long id);
}
