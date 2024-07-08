package com.thanhtuyen.webbook.mapper;

import com.thanhtuyen.webbook.dto.AuthorDTO;
import com.thanhtuyen.webbook.dto.BookDTO;
import com.thanhtuyen.webbook.entities.Book;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {
    public static Book toEntity(BookDTO bookDTO){
        Book book = new Book();
        if (bookDTO.getId() != null){
            book.setId(book.getId());
        }
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setPage(bookDTO.getPage());
        book.setLanguage(bookDTO.getLanguage());
        book.setDescription(bookDTO.getDescription());
        book.setCover(bookDTO.getCover());
        return book;
    }

    public static Book toEntity(Book bookOld, BookDTO bookDTO){
        bookOld.setAuthor(bookDTO.getAuthor());
        bookOld.setIsbn(bookDTO.getIsbn());
        bookOld.setTitle(bookDTO.getTitle());
        bookOld.setPage(bookDTO.getPage());
        bookOld.setLanguage(bookDTO.getLanguage());
        bookOld.setDescription(bookDTO.getDescription());
        return bookOld;
    }

    public static BookDTO toDto(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setAuthor(book.getAuthor()); // ? bookDTO.getAuthor()
        bookDTO.setDescription(book.getDescription());
        bookDTO.setPage(book.getPage());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setLanguage(book.getLanguage());
        bookDTO.set_active(book.is_active());
        bookDTO.setCover(book.getCover());
        return bookDTO;
    }

    public static List<BookDTO> toDtos(List<Book> books){
        List<BookDTO> bookDTOS = new ArrayList<>();
        books.forEach(b -> {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(b.getId());
            bookDTO.setIsbn(b.getIsbn());
            bookDTO.setAuthor(b.getAuthor());
            bookDTO.setDescription(b.getDescription());
            bookDTO.setPage(b.getPage());
            bookDTO.setTitle(b.getTitle());
            bookDTO.setLanguage(b.getLanguage());
            bookDTO.set_active(b.is_active());
            bookDTO.setCover(b.getCover());
            bookDTOS.add(bookDTO);
        });
        return bookDTOS;
    }
}
