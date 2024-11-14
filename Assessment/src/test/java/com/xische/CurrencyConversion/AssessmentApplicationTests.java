package com.xische.CurrencyConversion;

import com.google.gson.Gson;
import com.xische.CurrencyConversion.Dao.ConversionDao;
import com.xische.CurrencyConversion.Enums.ItemCategory;
import com.xische.CurrencyConversion.Enums.UserType;
import com.xische.CurrencyConversion.Service.DiscountStrategy;
import com.xische.CurrencyConversion.Service.Impl.*;
import com.xische.CurrencyConversion.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AssessmentApplicationTests {
		@Mock
		private ConversionDao conversionDao;

		@InjectMocks
		private CurrencyConversionImpl currencyConversionImpl;

		private BillingInfo billingInfo;
		private User user;

		private ExchangeRateResponse exchangeRateResponse;

		@BeforeEach
		public void setUp() {
			MockitoAnnotations.openMocks(this);
			// Setup default billingInfo and exchangeRateResponse for testing
			billingInfo = new BillingInfo();
			billingInfo.setId("3121");
			user = new User();
			user.setId("17985374");
			user.setName("Nikhil");
			exchangeRateResponse = new Gson().fromJson("{\n" +
					" \"result\":\"success\",\n" +
					" \"documentation\":\"https://www.exchangerate-api.com/docs\",\n" +
					" \"terms_of_use\":\"https://www.exchangerate-api.com/terms\",\n" +
					" \"time_last_update_unix\":1731542401,\n" +
					" \"time_last_update_utc\":\"Thu, 14 Nov 2024 00:00:01 +0000\",\n" +
					" \"time_next_update_unix\":1731628801,\n" +
					" \"time_next_update_utc\":\"Fri, 15 Nov 2024 00:00:01 +0000\",\n" +
					" \"base_code\":\"USD\",\n" +
					" \"conversion_rates\":{\n" +
					"  \"USD\":1,\n" +
					"  \"AED\":3.6725,\n" +
					"  \"AFN\":67.9230,\n" +
					"  \"ALL\":92.4364,\n" +
					"  \"AMD\":387.7507,\n" +
					"  \"ANG\":1.7900,\n" +
					"  \"AOA\":933.3675,\n" +
					"  \"ARS\":999.2500,\n" +
					"  \"AUD\":1.5370,\n" +
					"  \"AWG\":1.7900,\n" +
					"  \"AZN\":1.6997,\n" +
					"  \"BAM\":1.8485,\n" +
					"  \"BBD\":2.0000,\n" +
					"  \"BDT\":119.5234,\n" +
					"  \"BGN\":1.8476,\n" +
					"  \"BHD\":0.3760,\n" +
					"  \"BIF\":2943.4860,\n" +
					"  \"BMD\":1.0000,\n" +
					"  \"BND\":1.3404,\n" +
					"  \"BOB\":6.9424,\n" +
					"  \"BRL\":5.7480,\n" +
					"  \"BSD\":1.0000,\n" +
					"  \"BTN\":84.4345,\n" +
					"  \"BWP\":13.5975,\n" +
					"  \"BYN\":3.3370,\n" +
					"  \"BZD\":2.0000,\n" +
					"  \"CAD\":1.3979,\n" +
					"  \"CDF\":2859.8414,\n" +
					"  \"CHF\":0.8845,\n" +
					"  \"CLP\":985.3461,\n" +
					"  \"CNY\":7.2300,\n" +
					"  \"COP\":4450.3131,\n" +
					"  \"CRC\":511.2318,\n" +
					"  \"CUP\":24.0000,\n" +
					"  \"CVE\":104.2145,\n" +
					"  \"CZK\":23.9183,\n" +
					"  \"DJF\":177.7210,\n" +
					"  \"DKK\":7.0548,\n" +
					"  \"DOP\":60.2958,\n" +
					"  \"DZD\":133.6775,\n" +
					"  \"EGP\":49.3051,\n" +
					"  \"ERN\":15.0000,\n" +
					"  \"ETB\":121.4342,\n" +
					"  \"EUR\":0.9451,\n" +
					"  \"FJD\":2.2627,\n" +
					"  \"FKP\":0.7861,\n" +
					"  \"FOK\":7.0544,\n" +
					"  \"GBP\":0.7861,\n" +
					"  \"GEL\":2.7408,\n" +
					"  \"GGP\":0.7861,\n" +
					"  \"GHS\":16.2158,\n" +
					"  \"GIP\":0.7861,\n" +
					"  \"GMD\":71.6948,\n" +
					"  \"GNF\":8601.9166,\n" +
					"  \"GTQ\":7.7363,\n" +
					"  \"GYD\":209.4095,\n" +
					"  \"HKD\":7.7803,\n" +
					"  \"HNL\":25.2664,\n" +
					"  \"HRK\":7.1211,\n" +
					"  \"HTG\":131.6071,\n" +
					"  \"HUF\":385.8966,\n" +
					"  \"IDR\":15775.9545,\n" +
					"  \"ILS\":3.7485,\n" +
					"  \"IMP\":0.7861,\n" +
					"  \"INR\":84.4382,\n" +
					"  \"IQD\":1310.3919,\n" +
					"  \"IRR\":42135.5407,\n" +
					"  \"ISK\":139.2006,\n" +
					"  \"JEP\":0.7861,\n" +
					"  \"JMD\":158.7954,\n" +
					"  \"JOD\":0.7090,\n" +
					"  \"JPY\":155.2112,\n" +
					"  \"KES\":129.2587,\n" +
					"  \"KGS\":86.1677,\n" +
					"  \"KHR\":4051.8481,\n" +
					"  \"KID\":1.5369,\n" +
					"  \"KMF\":464.9722,\n" +
					"  \"KRW\":1403.6229,\n" +
					"  \"KWD\":0.3077,\n" +
					"  \"KYD\":0.8333,\n" +
					"  \"KZT\":492.9934,\n" +
					"  \"LAK\":21982.2721,\n" +
					"  \"LBP\":89500.0000,\n" +
					"  \"LKR\":292.3019,\n" +
					"  \"LRD\":189.0057,\n" +
					"  \"LSL\":18.1860,\n" +
					"  \"LYD\":4.8501,\n" +
					"  \"MAD\":9.9289,\n" +
					"  \"MDL\":18.0296,\n" +
					"  \"MGA\":4665.7775,\n" +
					"  \"MKD\":58.0667,\n" +
					"  \"MMK\":2102.4451,\n" +
					"  \"MNT\":3424.9271,\n" +
					"  \"MOP\":8.0136,\n" +
					"  \"MRU\":39.9461,\n" +
					"  \"MUR\":46.9773,\n" +
					"  \"MVR\":15.4589,\n" +
					"  \"MWK\":1741.2822,\n" +
					"  \"MXN\":20.5341,\n" +
					"  \"MYR\":4.4474,\n" +
					"  \"MZN\":63.9612,\n" +
					"  \"NAD\":18.1860,\n" +
					"  \"NGN\":1678.6660,\n" +
					"  \"NIO\":36.8193,\n" +
					"  \"NOK\":11.1138,\n" +
					"  \"NPR\":135.0952,\n" +
					"  \"NZD\":1.6953,\n" +
					"  \"OMR\":0.3845,\n" +
					"  \"PAB\":1.0000,\n" +
					"  \"PEN\":3.7990,\n" +
					"  \"PGK\":4.0193,\n" +
					"  \"PHP\":58.7384,\n" +
					"  \"PKR\":277.9054,\n" +
					"  \"PLN\":4.0999,\n" +
					"  \"PYG\":7841.7000,\n" +
					"  \"QAR\":3.6400,\n" +
					"  \"RON\":4.6839,\n" +
					"  \"RSD\":110.2478,\n" +
					"  \"RUB\":98.4531,\n" +
					"  \"RWF\":1377.3223,\n" +
					"  \"SAR\":3.7500,\n" +
					"  \"SBD\":8.5099,\n" +
					"  \"SCR\":13.6392,\n" +
					"  \"SDG\":545.1135,\n" +
					"  \"SEK\":10.9571,\n" +
					"  \"SGD\":1.3405,\n" +
					"  \"SHP\":0.7861,\n" +
					"  \"SLE\":22.8387,\n" +
					"  \"SLL\":22838.7053,\n" +
					"  \"SOS\":571.9601,\n" +
					"  \"SRD\":35.1824,\n" +
					"  \"SSP\":3382.0675,\n" +
					"  \"STN\":23.1556,\n" +
					"  \"SYP\":12955.7828,\n" +
					"  \"SZL\":18.1860,\n" +
					"  \"THB\":34.7778,\n" +
					"  \"TJS\":10.6834,\n" +
					"  \"TMT\":3.5026,\n" +
					"  \"TND\":3.1463,\n" +
					"  \"TOP\":2.3648,\n" +
					"  \"TRY\":34.3632,\n" +
					"  \"TTD\":6.7818,\n" +
					"  \"TVD\":1.5369,\n" +
					"  \"TWD\":32.4609,\n" +
					"  \"TZS\":2649.8542,\n" +
					"  \"UAH\":41.3482,\n" +
					"  \"UGX\":3668.3179,\n" +
					"  \"UYU\":42.1902,\n" +
					"  \"UZS\":12803.8747,\n" +
					"  \"VES\":45.0530,\n" +
					"  \"VND\":25345.9348,\n" +
					"  \"VUV\":121.7228,\n" +
					"  \"WST\":2.7751,\n" +
					"  \"XAF\":619.9630,\n" +
					"  \"XCD\":2.7000,\n" +
					"  \"XDR\":0.7583,\n" +
					"  \"XOF\":619.9630,\n" +
					"  \"XPF\":112.7839,\n" +
					"  \"YER\":250.5107,\n" +
					"  \"ZAR\":18.1596,\n" +
					"  \"ZMW\":27.2731,\n" +
					"  \"ZWL\":25.2857\n" +
					" }\n" +
					"}",ExchangeRateResponse.class);
		}

	@Test
	public void test_case_1_CalculateFinalAmount_WithDiscount_And_Conversion() {

		user.setType(UserType.AFFILIATE);
		user.setCustomerTenureInYears(1);
		billingInfo.setUser(user); // Configure user as needed
		billingInfo.setOriginalCurrency("INR");
		billingInfo.setTargetCurrency("USD");
		billingInfo.setItems(Arrays.asList(
				new Item("1",ItemCategory.STATIONERY, 500.0),
				new Item("2",ItemCategory.CUTLERY, 1000.0)
		));
		billingInfo.setTotalAmount(1500.0);
		when(conversionDao.findExchangeRates()).thenReturn(exchangeRateResponse);
		assertEquals(15, currencyConversionImpl.calculateFinalAmount(billingInfo)); // Example expected value; adjust based on calculations
	}

	@Test
	public void test_case_2_CalculateFinalAmount_NoPercentageDiscount_GroceryOnly() {

		user.setType(UserType.EMPLOYEE);
		user.setCustomerTenureInYears(1);
		billingInfo.setUser(user); // Configure user as needed
		billingInfo.setOriginalCurrency("INR");
		billingInfo.setTargetCurrency("USD");
		billingInfo.setItems(Arrays.asList(
				new Item("1",ItemCategory.GROCERIES, 500.0)
		));
		billingInfo.setTotalAmount(500.0);
		when(conversionDao.findExchangeRates()).thenReturn(exchangeRateResponse);
		assertEquals(5, currencyConversionImpl.calculateFinalAmount(billingInfo));
	}

	@Test
	public void test_case_3_testCalculateFinalAmount_NoDiscount() {

		user.setType(UserType.EMPLOYEE);
		user.setCustomerTenureInYears(1);
		billingInfo.setUser(user); // Configure user as needed
		billingInfo.setOriginalCurrency("INR");
		billingInfo.setTargetCurrency("USD");
		billingInfo.setItems(Arrays.asList(
				new Item("1",ItemCategory.GROCERIES, 90.0)
		));
		billingInfo.setTotalAmount(90.0);
		when(conversionDao.findExchangeRates()).thenReturn(exchangeRateResponse);
		assertEquals(1, currencyConversionImpl.calculateFinalAmount(billingInfo));
	}
	@Test
	public void test_case_4_testCalculateFinalAmount_NoDiscountStrategyAvailable() {
		user.setType(null);
		user.setCustomerTenureInYears(1);
		billingInfo.setUser(user); // Configure user as needed
		billingInfo.setOriginalCurrency("INR");
		billingInfo.setTargetCurrency("IDR");
		billingInfo.setItems(Arrays.asList(
				new Item("287",ItemCategory.VEGETABLES, 500.0)
		));
		billingInfo.setTotalAmount(500.0);
		when(conversionDao.findExchangeRates()).thenReturn(exchangeRateResponse);

		Double finalAmount = currencyConversionImpl.calculateFinalAmount(billingInfo);

		assertEquals(78879.77, finalAmount); // Adjust expected value as per calculation
	}

	@Test
	public void test_case_5_testCalculateFinalAmount_MultipleNonGroceryItems_And_Customer_Discount() {

		user.setType(UserType.CUSTOMER);
		user.setCustomerTenureInYears(3);
		billingInfo.setUser(user); // Configure user as needed
		billingInfo.setOriginalCurrency("INR");
		billingInfo.setTargetCurrency("USD");

		billingInfo.setTotalAmount(5000.0);
		billingInfo.setItems(Arrays.asList(
				new Item("1",ItemCategory.CLOTHES, 3000.0),
				new Item("2",ItemCategory.CUTLERY, 2000.0)
		));

		when(conversionDao.findExchangeRates()).thenReturn(exchangeRateResponse);
		Double finalAmount = currencyConversionImpl.calculateFinalAmount(billingInfo);

		assertEquals(56, finalAmount); // Adjust expected value based on calculations
	}


	@Test
	public void test_case_6_testCalculateFinalAmount_MissingExchangeRate() {

		user.setType(UserType.CUSTOMER);
		user.setCustomerTenureInYears(3);
		billingInfo.setUser(user); // Configure user as needed
		billingInfo.setOriginalCurrency("INR");
		billingInfo.setTargetCurrency("ABC");

		billingInfo.setTotalAmount(5000.0);
		billingInfo.setItems(Arrays.asList(
				new Item("1",ItemCategory.CLOTHES, 3000.0),
				new Item("2",ItemCategory.CUTLERY, 2000.0)
		));

		when(conversionDao.findExchangeRates()).thenReturn(exchangeRateResponse);
		Double finalAmount = currencyConversionImpl.calculateFinalAmount(billingInfo);

		assertEquals(0, finalAmount); // Adjust expected value based on calculations
	}

}
