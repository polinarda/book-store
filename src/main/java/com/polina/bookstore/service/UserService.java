package com.polina.bookstore.service;

import com.polina.bookstore.domain.Book;
import com.polina.bookstore.domain.Review;
import com.polina.bookstore.domain.User;

import java.util.List;

public interface UserService {

    User findUserById(Long userId);

    User findUserByEmail(String email);

    List<User> findAllUsers();

    List<Book> getCart(List<Long> perfumeIds);

    Book addReviewToPerfume(Review review, Long perfumeId);
}
