package com.example.application.data.service;

import com.example.application.data.entity.Password;
import com.example.application.data.repository.PasswordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordManagerService {

    private final PasswordRepository passwordRepository;

    public PasswordManagerService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public List<Password> findAllPasswords(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return passwordRepository.findAll();
        } else {
            return passwordRepository.search(stringFilter);
        }
    }


    public long countPasswords() {
        return passwordRepository.count();
    }

    public void deletePassword(Password password) {
        passwordRepository.delete(password);
    }

    public void savePassword(Password password) {
        if (password == null) {
            System.err.println("Password is null. Are you sure you have connected your form to the application?");
            return;
        }
        passwordRepository.save(password);
    }

}