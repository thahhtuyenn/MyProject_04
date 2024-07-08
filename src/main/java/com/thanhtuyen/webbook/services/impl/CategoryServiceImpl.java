package com.thanhtuyen.webbook.services.impl;

import com.thanhtuyen.webbook.dto.CategoryDTO;
import com.thanhtuyen.webbook.entities.Category;
import com.thanhtuyen.webbook.mapper.CategoryMapper;
import com.thanhtuyen.webbook.repositories.CategoryRepository;
import com.thanhtuyen.webbook.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getList() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryDTO getById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
//        if (category.isPresent()){
//            return category.get();
//        }
//        return null;
        CategoryDTO categoryDTO = new CategoryDTO();
        if (category.isPresent())
            categoryDTO = CategoryMapper.toDto(category.get());

        return categoryDTO;
    }

    @Override
    public Category save(CategoryDTO category) {
        Category categoryId = categoryRepository.findByCategoryId(category.getId());
        Category categoryNew = new Category();
        if (categoryId != null){
            categoryNew = CategoryMapper.toEntity(categoryId, category);
        }else {
            categoryNew = CategoryMapper.toEntity(category);
            categoryNew.set_active(true);
        }
        return categoryRepository.save(categoryNew);
    }

    @Override
    public boolean enable(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category == null){
            return false;
        }
        Category categoryUpdate = category.get();

        categoryUpdate.set_active(true);
        categoryRepository.save(categoryUpdate);

        return true;
    }

    @Override
    public boolean disable(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category == null){
            return false;
        }

        Category categoryUpdate = category.get();
        categoryUpdate.set_active(false);
        categoryRepository.save(categoryUpdate);

        return true;
    }

    @Override
    public List<Category> getByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public CategoryDTO getByCategoryId(Long id) {
        Optional<Category> opt = categoryRepository.findById(id);
        if (opt.isPresent()){
            return CategoryMapper.toDto(opt.get());
        }
        return null;
    }
}
