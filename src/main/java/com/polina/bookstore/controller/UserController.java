package com.polina.bookstore.controller;

import com.polina.bookstore.dto.order.OrderRequest;
import com.polina.bookstore.dto.order.OrderResponse;
import com.polina.bookstore.dto.book.BookResponse;
import com.polina.bookstore.dto.user.UserRequest;
import com.polina.bookstore.dto.user.UserResponse;
import com.polina.bookstore.exception.InputFieldException;
import com.polina.bookstore.mapper.OrderMapper;
import com.polina.bookstore.mapper.UserMapper;
import com.polina.bookstore.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserMapper userMapper;
    private final OrderMapper orderMapper;

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserInfo(@AuthenticationPrincipal UserPrincipal user) {
        return ResponseEntity.ok(userMapper.findUserByEmail(user.getEmail()));
    }

    @PostMapping("/cart")
    public ResponseEntity<List<BookResponse>> getCart(@RequestBody List<Long> perfumesIds) {
        return ResponseEntity.ok(userMapper.getCart(perfumesIds));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@AuthenticationPrincipal UserPrincipal user) {
        return ResponseEntity.ok(orderMapper.findOrderByEmail(user.getEmail()));
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> postOrder(@Valid @RequestBody OrderRequest order) {
            return ResponseEntity.ok(orderMapper.postOrder(order));
    }
}
