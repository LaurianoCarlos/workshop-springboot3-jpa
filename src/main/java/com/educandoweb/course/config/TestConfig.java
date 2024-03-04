package com.educandoweb.course.config;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(null, "Maria Madalena", "maria1234@gmail.com", "119872732", "1111");
        User user2 = new User(null, "Pedro Lima", "pedro1234@gmail.com", "119223121", "2222");

        Order order1 = new Order(null, Instant.parse("2023-02-04T12:02:06Z"), OrderStatus.PAID, user1);
        Order order2 = new Order(null, Instant.parse("2023-02-04T12:03:07Z"),OrderStatus.WAITING_PAYMENT, user2);
        Order order3 = new Order(null, Instant.parse("2023-02-04T12:04:12Z"),OrderStatus.WAITING_PAYMENT, user1);
        userRepository.saveAll(Arrays.asList(user1, user2));
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));
    }
}
