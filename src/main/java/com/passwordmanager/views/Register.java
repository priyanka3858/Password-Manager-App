package com.passwordmanager.views;

import com.passwordmanager.data.entity.Role;
import com.passwordmanager.data.entity.User;
import com.passwordmanager.data.repository.UserRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.RouterLink;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.Collections;

@Route("register")
@PageTitle("Register | Password Manager")
@AnonymousAllowed

public class Register extends Composite {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Register(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected Component initContent() {
        TextField name = new TextField("Name");
        TextField username = new TextField("Username");
        PasswordField password1 = new PasswordField("Password");
        PasswordField password2 = new PasswordField("Confirm password");
        VerticalLayout mainLayout = new VerticalLayout(
                new H2("Register"),
                name,
                username,
                password1,
                password2,
                new Button("Register", event -> register(
                        name.getValue(),
                        username.getValue(),
                        password1.getValue(),
                        password2.getValue()
                )),
                new RouterLink("Login", Login.class)
        );

        mainLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        return mainLayout;
    }

    private void register(String name, String username, String password1, String password2) {
        if (username.trim().isEmpty()) {
            Notification.show("Enter a username");
        } else if (password1.isEmpty()) {
            Notification.show("Enter a password");
        } else if (!password1.equals(password2)) {
            Notification.show("Passwords don't match");
        }
//        else if(userRepository.findByUsername(username) != null) {
//            Notification.show("Username already exists");
//        }
        else {
            User user = new User();
            user.setName(name);
            user.setUsername(username);
            user.setHashedPassword( passwordEncoder.encode(password1));
            user.setProfilePictureUrl(
                    "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);

            Notification.show("Your user is created, please login with new credentials");
        }
    }
}