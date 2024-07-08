package com.thanhtuyen.webbook.services;

import com.thanhtuyen.webbook.dto.AuthorDTO;
import com.thanhtuyen.webbook.entities.Author;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import java.util.List;

public interface AuthorService {
    public List<Author> getList();
    public Author getById(Long id);
    public Author save(AuthorDTO author);
    public boolean enable(Long id);
    public boolean disable(Long id);
    public List<Author> getByName(String name);
    public List<Author> findAllByActive(boolean is_active);
}
