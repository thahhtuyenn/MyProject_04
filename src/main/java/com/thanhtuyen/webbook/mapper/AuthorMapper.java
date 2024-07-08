package com.thanhtuyen.webbook.mapper;

import com.thanhtuyen.webbook.dto.AuthorDTO;
import com.thanhtuyen.webbook.entities.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorMapper {
    public static Author toEntity(AuthorDTO authorDTO){
        Author author = new Author();
        if (authorDTO.getId() != null){
            author.setId(authorDTO.getId());
        }
        author.setName(authorDTO.getName());
        author.setEmail(authorDTO.getEmail());
        author.setPhone(authorDTO.getPhone());
        author.setAddress(authorDTO.getAddress());
        return author;
    }

    public static Author toEntity(Author authorOld, AuthorDTO authorDTO){
        authorOld.setName(authorDTO.getName());
        authorOld.setEmail(authorDTO.getEmail());
        authorOld.setPhone(authorDTO.getPhone());
        authorOld.setAddress(authorDTO.getAddress());
        return authorOld;
    }

    public static AuthorDTO toDto(Author author){
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setAddress(author.getAddress());
        authorDTO.setPhone(author.getPhone());
        authorDTO.setEmail(author.getEmail());
        authorDTO.set_active(author.is_active());
        return authorDTO;
    }

    public static List<AuthorDTO> toDtos(List<Author> authors){
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        authors.forEach(a -> {
            authorDTOS.add(toDto(a));
        });
        return authorDTOS;
    }
}
