package com.polina.bookstore.dto.book;

import com.polina.bookstore.dto.author.AuthorRequest;
import lombok.Data;

@Data
public class BookSearchRequest {
    private AuthorRequest author;
    private String genre;
}
