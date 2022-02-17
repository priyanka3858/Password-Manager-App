package com.passwordmanager;

import com.passwordmanager.data.service.PasswordSearch;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "passwordmanager")
@PWA(name = "Password Manager", shortName = "Password Manager", offlineResources = {"images/logo.png"})
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class Application implements AppShellConfigurator {

    public static void main(String[] args) throws InterruptedException {
//        SerializationDemo serializationDemo = new SerializationDemo();
//        serializationDemo.serialization();

//        PasswordSearch passwordSearch = new PasswordSearch();
//        passwordSearch.TestFixedPool();
//        passwordSearch.TestDynamicPool();

        SpringApplication.run(Application.class, args);
    }

}
