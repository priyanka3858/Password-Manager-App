package com.example.application.views.list;

import com.example.application.data.entity.Password;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

class PasswordForm extends FormLayout {
    Binder<Password> binder = new BeanValidationBinder<>(Password.class);
    private Password pass;

    TextField url = new TextField("URL");
    TextField website = new TextField("Website");
    TextField username = new TextField("Username");
    TextField password = new TextField("Password");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public PasswordForm() {
        addClassName("password-form");

        binder.bindInstanceFields(this);


        add(url,
                website,
                username,
                password,
                createButtonsLayout());
    }

    public void setPassword(Password pass) {
        this.pass = pass;
        binder.readBean(pass);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, pass)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

//        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));


        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(pass);
            fireEvent(new SaveEvent(this, pass));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class PasswordFormEvent extends ComponentEvent<PasswordForm> {
        private Password pass;

        protected PasswordFormEvent(PasswordForm source, Password pass) {
            super(source, false);
            this.pass = pass;
        }

        public Password getPassword() {
            return pass;
        }
    }

    public static class SaveEvent extends PasswordFormEvent {
        SaveEvent(PasswordForm source, Password pass) {
            super(source, pass);
        }
    }

    public static class DeleteEvent extends PasswordFormEvent {
        DeleteEvent(PasswordForm source, Password pass) {
            super(source, pass);
        }

    }

    public static class CloseEvent extends PasswordFormEvent {
        CloseEvent(PasswordForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}