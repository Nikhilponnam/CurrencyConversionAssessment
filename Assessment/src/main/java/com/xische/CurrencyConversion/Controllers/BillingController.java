package com.xische.CurrencyConversion.Controllers;

import com.xische.CurrencyConversion.Service.CurrencyConversion;
import com.xische.CurrencyConversion.models.BillingInfo;
import com.xische.CurrencyConversion.models.BillingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingController {

    @Autowired
    CurrencyConversion currencyConversionService;

    @PostMapping("/api/calculate")
    public double convertBillAmount(@RequestBody BillingInfo billingInfo){
        return currencyConversionService.calculateFinalAmount(billingInfo);
    }
}
