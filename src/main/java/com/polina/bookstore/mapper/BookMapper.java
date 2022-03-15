package com.polina.bookstore.mapper;

import com.polina.bookstore.domain.Author;
import com.polina.bookstore.domain.Book;
import com.polina.bookstore.dto.author.AuthorRequest;
import com.polina.bookstore.dto.book.BookRequest;
import com.polina.bookstore.dto.book.BookResponse;
import com.polina.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final ModelMapper modelMapper;
    private final BookService bookService;

    private Book convertToBookEntity(BookRequest bookRequest) {
        return modelMapper.map(bookRequest, Book.class);
    }
    private Author convertToAuthorEntity(AuthorRequest authorRequest) {
        return modelMapper.map(authorRequest, Author.class);
    }

    BookResponse convertToResponseDto(Book book) {
        return modelMapper.map(book, BookResponse.class);
    }

    List<BookResponse> convertListToResponseDto(List<Book> perfumes) {
        return perfumes.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public BookResponse findBookById(Long perfumeId) {
        return convertToResponseDto(bookService.findBookById(perfumeId));
    }

    public List<BookResponse> findAllByUserId(Long id) {
        return convertListToResponseDto(bookService.findAllByUserId(id));
    }

    public List<BookResponse> findAllByAuthor(AuthorRequest author) {
        return convertListToResponseDto(bookService.filterByAuthor(convertToAuthorEntity(author)));
    }

    public List<BookResponse> filterByName(String name) {
        return convertListToResponseDto(bookService.filterByName(name));
    }

    public List<BookResponse> filterByGenre(String genre) {
        return convertListToResponseDto(bookService.filterByGenre(genre));
    }

    public BookResponse saveBook(BookRequest bookRequest) {
        return convertToResponseDto(bookService.save(convertToBookEntity(bookRequest)));
    }

    public void delete(Book book){
        this.bookService.delete(book);
    }

    public void delete(Long id){
        this.bookService.deleteBookById(id);
    }

}
