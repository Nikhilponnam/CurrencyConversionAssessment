package com.xische.CurrencyConversion.models;

import com.xische.CurrencyConversion.Enums.UserType;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class User {

    @Id
    String id;
    String name;
    UserType type;
    int customerTenureInYears;
}
