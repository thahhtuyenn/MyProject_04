package com.thanhtuyen.webbook.repositories;

import com.thanhtuyen.webbook.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a WHERE a.name LIKE %:name%")
    public List<Author> findByName(@Param("name") String name);

    @Query("SELECT a FROM Author a WHERE a.is_active = true")
    public List<Author> findAuthorByActive();

    @Query("SELECT a FROM Author a WHERE a.is_active = ?1")
    public List<Author> findAllBy_active(boolean is_active);

    @Query("SELECT a FROM Author a WHERE a.id = ?1")
    public Author findByAuthorId(Long id);
}
