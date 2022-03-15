package com.polina.bookstore.controller;

import com.polina.bookstore.dto.author.AuthorRequest;
import com.polina.bookstore.dto.book.BookRequest;
import com.polina.bookstore.dto.book.BookResponse;
import com.polina.bookstore.mapper.BookMapper;
import com.polina.bookstore.mapper.UserMapper;
import com.polina.bookstore.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookMapper bookMapper;
    private final UserMapper userMapper;


    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("id") Long perfumeId) {
        return ResponseEntity.ok(bookMapper.findBookById(perfumeId));
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<BookResponse>> getBookByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(bookMapper.filterByName(name));
    }

    @GetMapping("/search/author")
    public ResponseEntity<List<BookResponse>> filterBooksByAuthor(@RequestBody AuthorRequest authorRequest) {
        return ResponseEntity.ok(bookMapper.findAllByAuthor(authorRequest));
    }

    @GetMapping("/search/genre")
    public ResponseEntity<List<BookResponse>> filterBooksByGenre(@RequestBody String genre) {
        return ResponseEntity.ok(bookMapper.filterByGenre(genre));
    }

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<BookResponse> addBook(@AuthenticationPrincipal UserPrincipal user, @RequestBody BookRequest bookRequest) {
        bookRequest.setUser(userMapper.findRawUserById(user.getId()));
        return ResponseEntity.ok(bookMapper.saveBook(bookRequest));
    }

    @Transactional
    @PutMapping("/edit")
    public ResponseEntity<?> editBook(@AuthenticationPrincipal UserPrincipal user, @RequestBody BookRequest bookRequest) {
        try {
            bookMapper.delete(bookRequest.getId());
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
        bookRequest.setUser(userMapper.findRawUserById(user.getId()));
        return ResponseEntity.ok(bookMapper.saveBook(bookRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity.BodyBuilder deletePerfume(@AuthenticationPrincipal UserPrincipal user,
                                                            @RequestBody BookRequest bookRequest) {
        try {
            bookMapper.delete(bookRequest.getId()); return ResponseEntity.ok();
        }catch (Exception e){
            return ResponseEntity.status(500);
        }
    }

}
