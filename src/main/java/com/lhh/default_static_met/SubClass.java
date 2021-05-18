package com.lhh.default_static_met;

public class SubClass implements MyFunc,MyInterface{
    @Override
    public String getName() {
        return MyInterface.super.getName();
    }
}
