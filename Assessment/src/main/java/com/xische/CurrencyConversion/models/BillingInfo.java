package com.xische.CurrencyConversion.models;

import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
public class BillingInfo {
    @Id
    String id;
    List<Item> items;
    Double totalAmount;
    User user;
    String originalCurrency;
    String targetCurrency;
}
