package com.passwordmanager.data.generator;

import com.passwordmanager.data.entity.Role;
import com.passwordmanager.data.entity.Password;
import com.passwordmanager.data.entity.User;
import com.passwordmanager.data.repository.PasswordRepository;
import com.passwordmanager.data.repository.UserRepository;
import com.vaadin.exampledata.DataType;
import com.vaadin.exampledata.ExampleDataGenerator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(PasswordRepository passwordRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {

        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (passwordRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;


            logger.info("... generating 10 Password entities...");

            User user = new User();
            user.setName("John Normal");
            user.setUsername("user");
            user.setHashedPassword(passwordEncoder.encode("pass"));
            user.setProfilePictureUrl(
                    "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);

            ExampleDataGenerator<Password> passwordGenerator = new ExampleDataGenerator<>(Password.class,
                    LocalDateTime.now());
            passwordGenerator.setData(Password::setUrl, DataType.FIRST_NAME);
            passwordGenerator.setData(Password::setWebsite, DataType.DOMAIN);
            passwordGenerator.setData(Password::setUsername, DataType.EMAIL);
            passwordGenerator.setData(Password::setPassword, DataType.WORD);

            List<Password> passwords = passwordGenerator.create(10, seed).stream().peek(password -> {}).collect(Collectors.toList());

            passwordRepository.saveAll(passwords);

            logger.info("Generated demo data");
        };
    }

}
