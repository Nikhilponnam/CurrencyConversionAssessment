package com.xische.CurrencyConversion.models;

import lombok.Data;

@Data
public class BillingResponse {

    String result;
    Double finalBillAmount;
    String message;
}
