package com.testcontainers.userservice.containers;

import com.testcontainers.userservice.model.User;
import com.testcontainers.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
public class TestContainerExampleTest {

    @Container
    private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");

    @Test
    void nothing() { }

    @DynamicPropertySource
    public static void overrideContainerProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url",mySQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username",mySQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password",mySQLContainer::getPassword);
    }

    @Autowired
    private UserRepository userRepository;

    @Test
    void addNewUser_andGetCount() {
        User user = new User(1,"Steve");
        User savedUser = userRepository.save(user);

        List<User> userList = userRepository.findAll();

        assertEquals(1, userList.size());

    }

}
