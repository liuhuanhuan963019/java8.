package com.lhh.optional;

public class Goddness {

    private String name;

    public Goddness() {
    }

    public Goddness(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Goddness{" +
                "name='" + name + '\'' +
                '}';
    }
}
