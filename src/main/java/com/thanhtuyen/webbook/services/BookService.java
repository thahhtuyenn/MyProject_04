package com.thanhtuyen.webbook.services;

import com.thanhtuyen.webbook.dto.BookDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {
    public BookDTO findById(Long id);
    public BookDTO save(BookDTO bookDTO, MultipartFile imageProduct);
    public boolean disable(Long id);
    public boolean enable(Long id);
    public List<BookDTO> findAll();
}
