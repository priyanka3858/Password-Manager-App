package com.passwordmanager.data.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity
public class Password extends AbstractEntity {

    @NotEmpty
    private String url = "";

    @NotEmpty
    private String website = "";

    @NotEmpty
    private String username = "";

    @NotEmpty
    private String password = "";

    private UUID userId;

    @Override
    public String toString() {
        return url + " " + website;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {   return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}

