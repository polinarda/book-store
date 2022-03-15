package com.polina.bookstore.dto.book;

import com.polina.bookstore.domain.Author;
import com.polina.bookstore.domain.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookRequest {

    private Long id;
    private String name;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 255)
    private String description;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 255)
    private Author author;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 255)
    private String genre;

    @NotNull(message = "Fill in the input field")
    private Integer price;

    private User user;

}
