package com.passwordmanager.data.vector;

public class Sample {

    private String name;

    public Sample(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int parseInt(String name) {
        return Integer.parseInt(name);
    }
}

