package com.xische.CurrencyConversion.Enums;

public enum UserType {

    EMPLOYEE("EMPLOYEE"),
    AFFILIATE("AFFILIATE"),
    CUSTOMER("CUSTOMER");

    String name;

    private UserType(String name) {
        this.name = name;
    }
}
