package com.example.application.data.generator;

import com.example.application.data.entity.Password;
import com.example.application.data.repository.PasswordRepository;
import com.vaadin.exampledata.DataType;
import com.vaadin.exampledata.ExampleDataGenerator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(PasswordRepository passwordRepository) {

        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (passwordRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;


            logger.info("... generating 10 Password entities...");
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
