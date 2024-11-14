package com.xische.CurrencyConversion.Service.Impl;

import com.xische.CurrencyConversion.Dao.ConversionDao;
import com.xische.CurrencyConversion.Enums.ItemCategory;
import com.xische.CurrencyConversion.Service.CurrencyConversion;
import com.xische.CurrencyConversion.Service.DiscountStrategy;
import com.xische.CurrencyConversion.models.BillingInfo;
import com.xische.CurrencyConversion.models.BillingResponse;
import com.xische.CurrencyConversion.models.ExchangeRateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

@Service
@Slf4j
public class CurrencyConversionImpl implements CurrencyConversion {

    @Autowired
    ConversionDao conversionDao;
    @Override
    public double calculateFinalAmount(BillingInfo billingInfo) {
        Double totalDiscount = 0.0;
        DiscountStrategy discountStrategy = DiscountChain.getBestDiscount(billingInfo.getUser());

        if (Objects.nonNull(discountStrategy)) {
            totalDiscount = billingInfo.getItems().stream()
                    .filter(item -> !item.getCategory().equals(ItemCategory.GROCERIES))
                    .mapToDouble(item -> discountStrategy.applyDiscount(item.getPrice()))
                    .sum();
        }

        Double billAmount = billingInfo.getTotalAmount();

        // subtracting non-grocery items discount
        billAmount = billAmount - totalDiscount;

        // Converting billAmount into dollars
        int billAmountInDollars = convertBillAmountIntoDollars(billAmount, billingInfo.getOriginalCurrency());

        // Apply fixed discount
        FixedDiscountDecorator fixedDiscount = new FixedDiscountDecorator(billAmountInDollars);
        billAmount = fixedDiscount.applyFixedDiscount();

        //BillingResponse billingResponse =  convert(billAmount, billingInfo.getTargetCurrency());
        return convert(billAmount, billingInfo.getTargetCurrency());
    }

    public double convert(double amount, String targetCurrency) {
        // Fetch and apply the exchange rate
        BillingResponse billingResponse = new BillingResponse();
        double exchangeRate = getExchangeRate(targetCurrency);

            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.FLOOR);
            double result = Double.parseDouble(df.format(amount * exchangeRate));
            return result;
    }

    private double getExchangeRate(String currency) {
        ExchangeRateResponse exchangeRateResponse = (ExchangeRateResponse) conversionDao.findExchangeRates();
        if(exchangeRateResponse!=null && exchangeRateResponse.getConversion_rates()!=null) {
            return exchangeRateResponse.getConversion_rates().get(currency) !=null ? exchangeRateResponse.getConversion_rates().get(currency):0 ;
        }else{
            return 0; // failure scenario
        }
    }

    private int convertBillAmountIntoDollars(double billAmount,String currency){
        double exchangeRate = getExchangeRate(currency);
        return (int) ( billAmount / exchangeRate);
    }
}
