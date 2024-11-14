package com.xische.CurrencyConversion.Enums;

public enum ItemCategory {

    GROCERIES("GROCERIES"),
    VEGETABLES("VEGETABLES"),
    STATIONERY("STATIONERY"),
    CUTLERY("CUTLERY"),
    CLOTHES("CLOTHES");

    String name;

    private ItemCategory(String name) {
        this.name = name;
    }
}
