package com.polina.bookstore.service.Impl;

import com.polina.bookstore.domain.Book;
import com.polina.bookstore.domain.Review;
import com.polina.bookstore.domain.User;
import com.polina.bookstore.repository.BookRepository;
import com.polina.bookstore.repository.ReviewRepository;
import com.polina.bookstore.repository.UserRepository;
import com.polina.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllByOrderByIdAsc();
    }


    @Override
    public List<Book> getCart(List<Long> bookIds) {
        return bookRepository.findByIdIn(bookIds);
    }

    @Override
    public Book addReviewToPerfume(Review review, Long perfumeId) {
        Book book = bookRepository.getOne(perfumeId);
        List<Review> reviews = book.getReviews();
        reviews.add(review);
        double totalReviews = reviews.size();
        double sumRating = reviews.stream().mapToInt(Review::getRating).sum();
        book.setRating(sumRating / totalReviews);
        reviewRepository.save(review);
        return book;
    }
}
