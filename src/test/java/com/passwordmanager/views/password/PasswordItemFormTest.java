package com.passwordmanager.views.password;

import com.passwordmanager.data.entity.Password;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class PasswordItemFormTest {
    private Password pmvUser;

    @Before
    public void setupData() {

        pmvUser = new Password();
        pmvUser.setUrl("Priyanka");
        pmvUser.setWebsite("test.com");
        pmvUser.setUsername("priyanka@bu.edu");
        pmvUser.setPassword("password");
    }

    @Test
    public void formFieldsPopulated() {
        PasswordItemForm form = new PasswordItemForm();
        form.setPassword(pmvUser);
        Assert.assertEquals("Priyanka", form.url.getValue());
        Assert.assertEquals("test.com", form.website.getValue());
        Assert.assertEquals("priyanka@bu.edu", form.username.getValue());
        Assert.assertEquals("password", form.password.getValue());
    }

    @Test
    public void savePasswordHasCorrectValues() {
        PasswordItemForm form = new PasswordItemForm();
        Password password = new Password();
        form.setPassword(password);
        form.url.setValue("Priyanka");
        form.website.setValue("test.com");
        form.username.setValue("pmv@bu.edu");
        form.password.setValue("password");

        AtomicReference<Password> savedPasswordRef = new AtomicReference<>(null);
        form.addListener(PasswordItemForm.SaveEvent.class, e -> {
            savedPasswordRef.set(e.getPassword());
        });
        form.save.click();
        Password savedPassword = savedPasswordRef.get();

        Assert.assertEquals("Priyanka", savedPassword.getUrl());
        Assert.assertEquals("test.com", savedPassword.getWebsite());
        Assert.assertEquals("pmv@bu.edu", savedPassword.getUsername());
        Assert.assertEquals("password", savedPassword.getPassword());
    }
}