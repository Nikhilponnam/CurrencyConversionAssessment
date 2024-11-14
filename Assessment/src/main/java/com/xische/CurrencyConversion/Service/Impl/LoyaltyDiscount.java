package com.xische.CurrencyConversion.Service.Impl;

import com.xische.CurrencyConversion.Service.DiscountStrategy;

public class LoyaltyDiscount implements DiscountStrategy {
    public double applyDiscount(double amount) {
        return amount * 0.05; // 5% discount
    }
}
