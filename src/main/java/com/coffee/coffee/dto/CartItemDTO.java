package com.coffee.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CartItemDTO {
    private int quantity;
    private Double totalPrice;

    public CartItemDTO(int quantity, Double totalPrice) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

}

