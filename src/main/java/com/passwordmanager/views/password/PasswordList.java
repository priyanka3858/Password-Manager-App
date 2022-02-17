package com.passwordmanager.views.password;

import com.passwordmanager.data.entity.Password;
import com.passwordmanager.data.service.PasswordManagerService;
import com.passwordmanager.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route(value="", layout = MainLayout.class)
@PageTitle("Password Manager")
@PermitAll
public class PasswordList extends VerticalLayout {
    Grid<Password> grid = new Grid<>(Password.class);
    TextField filterText = new TextField();
    PasswordItemForm form;
    PasswordManagerService service;

    public PasswordList(PasswordManagerService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setPassword(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new PasswordItemForm();
        form.setWidth("25em");

        form.addListener(PasswordItemForm.SaveEvent.class, this::savePassword);
        form.addListener(PasswordItemForm.DeleteEvent.class, this::deletePassword);
        form.addListener(PasswordItemForm.CloseEvent.class, e -> closeEditor());
    }

    private void savePassword(PasswordItemForm.SaveEvent event) {
        service.savePassword(event.getPassword());
        updateList();
        closeEditor();
    }

    private void deletePassword(PasswordItemForm.DeleteEvent event) {
        service.deletePassword(event.getPassword());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("password-item-grid");
        grid.setSizeFull();
        grid.setColumns("url", "website", "username", "password");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(e-> editPassword(e.getValue()) );
    }

    private void editPassword(Password password) {
        if (password == null) {
            closeEditor();
        } else {
            form.setPassword(password);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addPasswordButton = new Button("Add Password");
        addPasswordButton.addClickListener(e -> addPassword());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addPasswordButton);

        toolbar.addClassName("toolbar");

        return toolbar;
    }

    private void addPassword() {
        grid.asSingleSelect().clear();
        editPassword(new Password());
    }

    private void updateList() {
        grid.setItems(service.findAllPasswords(filterText.getValue()));
    }
}