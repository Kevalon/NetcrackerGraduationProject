package com.netcracker.application.controller.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchForm {
    private String categoryName;
    private String productName;
    private String makerName;
    private Double minPrice;
    private Double maxPrice;
}
