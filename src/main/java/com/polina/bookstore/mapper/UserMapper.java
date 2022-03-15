package com.polina.bookstore.mapper;

import com.polina.bookstore.domain.Review;
import com.polina.bookstore.domain.User;
import com.polina.bookstore.dto.RegistrationRequest;
import com.polina.bookstore.dto.book.BookResponse;
import com.polina.bookstore.dto.review.ReviewRequest;
import com.polina.bookstore.dto.user.UserRequest;
import com.polina.bookstore.dto.user.UserResponse;
import com.polina.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;
    private final BookMapper bookMapper;
    private final UserService userService;

    private User convertToEntity(UserRequest userRequest) {
        return modelMapper.map(userRequest, User.class);
    }

    User convertToEntity(RegistrationRequest registrationRequest) {
        return modelMapper.map(registrationRequest, User.class);
    }

    private Review convertToEntity(ReviewRequest reviewRequest) {
        return modelMapper.map(reviewRequest, Review.class);
    }

    UserResponse convertToResponseDto(User user) {
        return modelMapper.map(user, UserResponse.class);
    }

    public UserResponse findUserById(Long userId) {
        return convertToResponseDto(userService.findUserById(userId));
    }

    public User findRawUserById(Long userId) {
        return userService.findUserById(userId);
    }

    public UserResponse findUserByEmail(String email) {
        return convertToResponseDto(userService.findUserByEmail(email));
    }

    public List<BookResponse> getCart(List<Long> perfumesIds) {
        return bookMapper.convertListToResponseDto(userService.getCart(perfumesIds));
    }

    public List<UserResponse> findAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public BookResponse addReviewToBook(ReviewRequest reviewRequest, Long perfumeId) {
        return bookMapper.convertToResponseDto(userService.addReviewToPerfume(convertToEntity(reviewRequest), perfumeId));
    }
}
