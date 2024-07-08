package com.thanhtuyen.webbook.repositories;

import com.thanhtuyen.webbook.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByName(String name);

    @Query("SELECT c FROM Category c WHERE c.name LIKE %:name%")
    List<Category> findByName1(@Param("name") String name);

    @Query(value = "SELECT * FROM categories WHERE name LIKE %?1%", nativeQuery = true)
    List<Category> findByName2(String name);

    @Query("SELECT c FROM Category c WHERE c.id = :id")
    Category findByCategoryId(@Param("id") Long id);
}
