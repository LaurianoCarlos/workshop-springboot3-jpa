package com.educandoweb.course.services;

import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.OrderItemRepository;
import com.educandoweb.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    public OrderItem findById(Long id) {
        Optional<OrderItem> obj = orderItemRepository.findById(id);
        return obj.get();
    }
}
