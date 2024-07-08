package com.thanhtuyen.webbook.services.impl;

import com.thanhtuyen.webbook.dto.AuthorDTO;
import com.thanhtuyen.webbook.entities.Author;
import com.thanhtuyen.webbook.mapper.AuthorMapper;
import com.thanhtuyen.webbook.repositories.AuthorRepository;
import com.thanhtuyen.webbook.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getList() {
        return authorRepository.findAll();
    }

    @Override
    public Author getById(Long id) {

        Optional<Author> author = authorRepository.findById(id);
        return author.orElse(null);
    }

    @Override
    public Author save(AuthorDTO author) {
        Author authorOld = authorRepository.findByAuthorId(author.getId());
        Author authorNew = new Author();
        if (authorOld != null){
            authorNew = AuthorMapper.toEntity(authorOld, author);
        }else {
            authorNew = AuthorMapper.toEntity(author);
            authorNew.set_active(true);
        }

        return authorRepository.save(authorNew);
    }

    @Override
    public boolean enable(Long id) {
        Author author = getById(id);
        if (author == null){
            return false;
        }

        author.set_active(true);
        authorRepository.save(author);
        return true;
    }

    @Override
    public boolean disable(Long id) {
        Author author = getById(id);
        if (author == null){
            return false;
        }

        author.set_active(false);
        authorRepository.save(author);
        return true;
    }

    @Override
    public List<Author> getByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public List<Author> findAllByActive(boolean is_active) {
        return authorRepository.findAllBy_active(is_active);
    }
}
