package com.thanhtuyen.webbook.services.impl;

import com.thanhtuyen.webbook.dto.BookDTO;
import com.thanhtuyen.webbook.entities.Book;
import com.thanhtuyen.webbook.mapper.BookMapper;
import com.thanhtuyen.webbook.repositories.BookRepository;
import com.thanhtuyen.webbook.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDTO findById(Long id) {
        Book book = bookRepository.findByBookId(id);
        BookDTO bookDTO = null;
        if (book != null){
            bookDTO = BookMapper.toDto(book);
        }
        return bookDTO;
    }

    @Override
    public BookDTO save(BookDTO bookDTO, MultipartFile imageProduct) {
        try {
            Book book = bookRepository.findByBookId(bookDTO.getId());
            Book bookNew = new Book();
            if (book != null){
                bookNew = BookMapper.toEntity(book, bookDTO);
                bookNew.setCover(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }else {
                bookNew = BookMapper.toEntity(bookDTO);
                bookNew.setCover(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            return BookMapper.toDto(bookRepository.save(bookNew));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean disable(Long id) {
        Book book = bookRepository.findByBookId(id);
        if (id == null){
            return false;
        }

        book.set_active(false);
        bookRepository.save(book);
        return true;
    }

    @Override
    public boolean enable(Long id) {
        Book book = bookRepository.findByBookId(id);
        if (id == null){
            return false;
        }

        book.set_active(true);
        bookRepository.save(book);
        return true;
    }

    @Override
    public List<BookDTO> findAll() {
        List<Book> books = bookRepository.findAll();
         List<BookDTO> dtos = BookMapper.toDtos(books);
         System.out.println(dtos);
        return dtos;
    }
}
