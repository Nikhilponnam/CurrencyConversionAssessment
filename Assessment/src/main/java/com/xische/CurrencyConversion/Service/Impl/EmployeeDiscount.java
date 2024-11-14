package com.xische.CurrencyConversion.Service.Impl;

import com.xische.CurrencyConversion.Service.DiscountStrategy;

public class EmployeeDiscount implements DiscountStrategy {
        public double applyDiscount(double amount) {
            return amount * 0.30; // 30% discount
        }
}
