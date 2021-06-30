package dev.luanfernandes.loja.vo;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@AllArgsConstructor
@ToString
public class SalesReportVO {
    private final String productName;
    private final Long soldQuantity;
    private final LocalDate lastSale;
}
