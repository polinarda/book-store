package com.polina.bookstore.dto.review;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ReviewRequest {

    private Long bookId;

    @NotBlank(message = "Fill in the input field")
    private String author;

    @NotBlank(message = "Fill in the input field")
    private String message;

    @Min(value = 1, message = "Choose perfume rating")
    private Integer rating;
}
