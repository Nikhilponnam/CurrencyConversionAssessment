package com.xische.CurrencyConversion.Service;

import com.xische.CurrencyConversion.models.BillingInfo;
import com.xische.CurrencyConversion.models.BillingResponse;

public interface CurrencyConversion {

    double calculateFinalAmount(BillingInfo billingInfo);
}
