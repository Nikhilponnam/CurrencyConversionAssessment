package com.xische.CurrencyConversion.Service.Impl;

import com.xische.CurrencyConversion.Service.DiscountDecorator;

public class FixedDiscountDecorator implements DiscountDecorator {
        private int amount;

        public FixedDiscountDecorator(int amount) {
            this.amount = amount;
        }

    @Override
    public double applyFixedDiscount() {
        int hundreds = amount / 100;
        return amount - (hundreds * 5);
    }
}
