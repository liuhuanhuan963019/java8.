package com.lhh.optional;

import java.util.Optional;

public class NewMan {

    private Optional<Goddness> goddness = Optional.empty();

    public NewMan(Optional<Goddness> goddness) {
        this.goddness = goddness;
    }

    public NewMan() {
    }

    public Optional<Goddness> getGoddness() {
        return goddness;
    }

    public void setGoddness(Optional<Goddness> goddness) {
        this.goddness = goddness;
    }

    @Override
    public String toString() {
        return "NewMan{" +
                "goddness=" + goddness +
                '}';
    }
}
