package com.passwordmanager.views;

import com.passwordmanager.data.service.PasswordManagerService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard | Password Manager")
@PermitAll
public class DashboardView extends VerticalLayout {
    private final PasswordManagerService service;

    public DashboardView(PasswordManagerService service) {
        this.service = service;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getPasswordStats());
    }

    private Component getPasswordStats() {
        Span stats = new Span("You have "+ service.countPasswords() + " passwords in Password Manager");
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }

}