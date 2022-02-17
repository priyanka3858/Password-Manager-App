package com.passwordmanager.data.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordSearchTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFixedPoolWithDynamicPool() throws InterruptedException {
        System.out.println("Test Fixed Pool With Pool Size = 5");
        PasswordSearch passwordSearch = new PasswordSearch();
        passwordSearch.TestFixedPool(5);
    }

    @Test
    void testDynamicPoolWithFixedPool() throws InterruptedException {
        System.out.println("Test Dynamic Pool");
        PasswordSearch passwordSearch = new PasswordSearch();
        passwordSearch.TestDynamicPool();
    }
}