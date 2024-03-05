package com.educandoweb.course.config;

import com.educandoweb.course.entities.*;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.*;
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

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(null, "Maria Madalena", "maria1234@gmail.com", "119872732", "1111");
        User user2 = new User(null, "Pedro Lima", "pedro1234@gmail.com", "119223121", "2222");

        Order order1 = new Order(null, Instant.parse("2023-02-04T12:02:06Z"), OrderStatus.PAID, user1);
        Order order2 = new Order(null, Instant.parse("2023-02-04T12:03:07Z"),OrderStatus.WAITING_PAYMENT, user2);
        Order order3 = new Order(null, Instant.parse("2023-02-04T12:04:12Z"),OrderStatus.WAITING_PAYMENT, user1);

        Category category1 = new Category(null, "Electronics");
        Category category2 = new Category(null, "Books");
        Category category3 = new Category(null, "Computers");

        Product product1 = new Product(null, "The lord od the Rings", "Lorem Ipsum has been the " +
            "industry" + "s standard dummy text ever since",22.99, null);
        Product product2 = new Product(null, "Smart TV", "Lorem Ipsum has been the " +
                "industry",1200.00, null);
        Product product3 = new Product(null, "Makebook Pro", "Lorem Ipsum has bees standard dummy" +
                " text ever since",10000.00, null);

        product2.getCategories().add(category2);
        product1.getCategories().add(category1);
        product1.getCategories().add(category3);

        userRepository.saveAll(Arrays.asList(user1, user2));
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));
        categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
        productRepository.saveAll(Arrays.asList(product1, product2, product3));

        OrderItem orderItem1 = new OrderItem(order1, product1, 2, product1.getPrice());
        OrderItem orderItem2 = new OrderItem(order2, product2, 3, product2.getPrice());
        OrderItem orderItem3 = new OrderItem(order3, product3, 1, product3.getPrice());

        orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3));

        Payment payment1 = new Payment(null, Instant.parse("2023-02-04T14:02:06Z"), order1);
        order1.setPayment(payment1);

        orderRepository.save(order1);

    }
}
