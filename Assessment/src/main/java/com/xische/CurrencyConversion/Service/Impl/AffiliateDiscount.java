package com.xische.CurrencyConversion.Service.Impl;

import com.xische.CurrencyConversion.Service.DiscountStrategy;

public class AffiliateDiscount implements DiscountStrategy {
    public double applyDiscount(double amount) {
        return amount * 0.10; // 10% discount
    }
}
