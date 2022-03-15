package com.polina.bookstore.service.Impl;

import com.polina.bookstore.domain.Author;
import com.polina.bookstore.domain.Book;
import com.polina.bookstore.repository.BookRepository;
import com.polina.bookstore.repository.ReviewRepository;
import com.polina.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Book findBookById(Long bookId) {
        return bookRepository.findById(bookId).get();
    }

    @Override
    public List<Book> findAllByUserId(Long id) {
        return bookRepository.findAllByUserId(id);
    }

    @Override
    public List<Book> filterByAuthor(Author author) {
        return bookRepository.findAllByAuthor(author);
    }

    @Override
    public List<Book> filterByName(String name) {
        return bookRepository.findAllByNameContains(name);
    }

    @Override
    public List<Book> filterByGenre(String genre) {
        return bookRepository.findBooksByGenreContains(genre);
    }

    @Override
    public void deleteBookById(Long id) {
        this.bookRepository.deleteBookById(id);
    }

    @Override
    public void delete(Book book) {
        this.bookRepository.delete(book);
    }

    @Override
    public Book save(Book book) {
        return this.bookRepository.save(book);
    }


}
