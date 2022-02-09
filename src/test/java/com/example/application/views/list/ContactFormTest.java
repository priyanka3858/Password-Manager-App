package com.example.application.views.list;

import com.example.application.data.entity.Password;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

class PasswordFormTest {
    private Password marcUsher;

    @Before 
    public void setupData() {

        marcUsher = new Password();
        marcUsher.setUrl("Marc");
        marcUsher.setWebsite("Usher");
        marcUsher.setUsername("marc@usher.com");
    }

    @Test
    public void formFieldsPopulated() {
        PasswordForm form = new PasswordForm();
        form.setPassword(marcUsher);
        Assert.assertEquals("Marc", form.url.getValue());
        Assert.assertEquals("Usher", form.website.getValue());
        Assert.assertEquals("marc@usher.com", form.username.getValue());
    }

    @Test
    public void saveEventHasCorrectValues() {
        PasswordForm form = new PasswordForm();
        Password password = new Password();
        form.setPassword(password);
        form.url.setValue("John");
        form.website.setValue("Doe");
        form.username.setValue("john@doe.com");

        AtomicReference<Password> savedPasswordRef = new AtomicReference<>(null);
        form.addListener(PasswordForm.SaveEvent.class, e -> {
            savedPasswordRef.set(e.getPassword());
        });
        form.save.click();
        Password savedPassword = savedPasswordRef.get();

        Assert.assertEquals("John", savedPassword.getUrl());
        Assert.assertEquals("Doe", savedPassword.getWebsite());
        Assert.assertEquals("john@doe.com", savedPassword.getUsername());
    }
}