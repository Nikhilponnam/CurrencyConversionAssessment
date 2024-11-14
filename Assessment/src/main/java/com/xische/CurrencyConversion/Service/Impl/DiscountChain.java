package com.xische.CurrencyConversion.Service.Impl;

import com.xische.CurrencyConversion.Enums.UserType;
import com.xische.CurrencyConversion.Service.DiscountStrategy;
import com.xische.CurrencyConversion.models.User;

// Chain of Responsibility to select the best discount
public class DiscountChain {
    public static DiscountStrategy getBestDiscount(User user) {
        if (user.getType() != null) {
            if (user.getType().equals(UserType.EMPLOYEE)) {
                return new EmployeeDiscount();
            }
            if (user.getType().equals(UserType.AFFILIATE)) {
                return new AffiliateDiscount();
            }
            if (user.getType().equals(UserType.CUSTOMER) && user.getCustomerTenureInYears() >= 2) {
                return new LoyaltyDiscount();
            }
        }
        return null; // No percentage-based discount applies
    }
}
