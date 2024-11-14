package com.xische.CurrencyConversion.models;

import com.xische.CurrencyConversion.Enums.ItemCategory;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.catalog.Catalog;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    String id;
    ItemCategory category;
    Double price;
}
