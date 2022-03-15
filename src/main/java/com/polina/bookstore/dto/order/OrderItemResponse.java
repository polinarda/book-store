package com.polina.bookstore.dto.order;

import com.polina.bookstore.dto.book.BookResponse;
import lombok.Data;

@Data
public class OrderItemResponse {
    private Long id;
    private Long amount;
    private Long quantity;
    private BookResponse book;
}
