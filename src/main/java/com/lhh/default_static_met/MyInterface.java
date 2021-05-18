package com.lhh.default_static_met;

public interface MyInterface {

    default String getName() {
        return "嘻嘻";
    }
}
