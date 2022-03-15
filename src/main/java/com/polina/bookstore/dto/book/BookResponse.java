package com.polina.bookstore.dto.book;

import lombok.Data;

@Data
public class BookResponse {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private String genre;
    private String author;
}
