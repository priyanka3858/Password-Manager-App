package com.passwordmanager.data.entity;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class Configuration{

    private UUID userId; // foregin key  ( primary key in users)

    private boolean darkMode;

    private int paginationLimit;

    private boolean notifyLeakedPasseord;

    private boolean notifyMasterPasswordChange;

    private boolean newsLetter;

    public Configuration(UUID userId, boolean darkMode, int paginationLimit, boolean notifyLeakedPasseord, boolean notifyMasterPasswordChange, boolean newsLetter) {
        this.userId = userId;
        this.darkMode = darkMode;
        this.paginationLimit = paginationLimit;
        this.notifyLeakedPasseord = notifyLeakedPasseord;
        this.notifyMasterPasswordChange = notifyMasterPasswordChange;
        this.newsLetter = newsLetter;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public int getPaginationLimit() {
        return paginationLimit;
    }

    public void setPaginationLimit(int paginationLimit) {
        this.paginationLimit = paginationLimit;
    }

    public boolean isNotifyLeakedPasseord() {
        return notifyLeakedPasseord;
    }

    public void setNotifyLeakedPasseord(boolean notifyLeakedPasseord) {
        this.notifyLeakedPasseord = notifyLeakedPasseord;
    }

    public boolean isNotifyMasterPasswordChange() {
        return notifyMasterPasswordChange;
    }

    public void setNotifyMasterPasswordChange(boolean notifyMasterPasswordChange) {
        this.notifyMasterPasswordChange = notifyMasterPasswordChange;
    }

    public boolean isNewsLetter() {
        return newsLetter;
    }

    public void setNewsLetter(boolean newsLetter) {
        this.newsLetter = newsLetter;
    }
}
