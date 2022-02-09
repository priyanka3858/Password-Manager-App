package com.passwordmanager.data.entity;
import com.passwordmanager.data.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.util.Collections;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @AfterEach
    public void tearDown() {
        userRepository = null;
    }

    @Test
    public void testUser() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void testAnotherUser() {
        User user = new User();
        user.setName("John Smith");
        user.setUsername("John123");
        String hashedPassword = passwordEncoder.encode("password");
        user.setHashedPassword(hashedPassword);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        assertNotNull(user);
        assertEquals("John Smith", user.getName());
        assertEquals("John123", user.getUsername());
        assertEquals(hashedPassword, user.getHashedPassword());
        assertEquals(Collections.singleton(Role.USER), user.getRoles());
        assertEquals(user, userRepository.findById(user.getId()).get());
    }
}