package com.thanhtuyen.webbook.dto;

import com.thanhtuyen.webbook.entities.Author;
import com.thanhtuyen.webbook.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private String  cover;
    private String description;
    private String isbn;
    private String language;
    private int page;
    private boolean is_active;
    private Author author;
    private Set<Category> categories;
}
