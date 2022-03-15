package com.polina.bookstore.repository;

import com.polina.bookstore.domain.Author;
import com.polina.bookstore.domain.Book;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookById(Long id);

    List<Book> findAllByUserId(Long id);

    List<Book> findAllByAuthor(Author author);

    List<Book> findAllByNameContains(String name);

    List<Book> findBooksByGenreContains(String genre);

    List<Book> findByIdIn(List<Long> bookIds);

    void deleteBookById(Long id);

    void delete(@NonNull Book book);

    Book save(@NonNull Book book);

}
