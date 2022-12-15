package com.example.backend.dto.function;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SumBook {
    BigDecimal price;
    Integer count;

    public SumBook() {

    }

    public SumBook(BigDecimal price, Integer count) {
        this.price = price;
        this.count = count;
    }

}