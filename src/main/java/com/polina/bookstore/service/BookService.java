package com.polina.bookstore.service;

import com.polina.bookstore.domain.Author;
import com.polina.bookstore.domain.Book;

import java.util.List;

public interface BookService {
    Book findBookById(Long id);

    List<Book> findAllByUserId(Long id);

    List<Book> filterByAuthor(Author author);

    List<Book> filterByName(String name);

    List<Book> filterByGenre(String genre);

    void deleteBookById(Long id);

    void delete(Book book);

    Book save(Book book);
}
