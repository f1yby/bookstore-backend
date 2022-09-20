package com.example.backend.dto;

import com.example.backend.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class UserWithBuyAmount extends User {
    BigDecimal buyAmount;

    public UserWithBuyAmount(User user) {
        super(user);
        buyAmount = BigDecimal.valueOf(0);
        user.getOrderItems().forEach(orderItem -> {
            if (!orderItem.isActivated()) {
                buyAmount = buyAmount.add(BigDecimal.valueOf(orderItem.getCount()).multiply(orderItem.getBook().getPrice()));
            }


        });
    }


    public UserWithBuyAmount(User user, Date start, Date end) {
        super(user);
        buyAmount = BigDecimal.valueOf(0);
        user.getOrderItems().forEach(orderItem -> {
            if (!orderItem.isActivated() && orderItem.getOrder().getDate().before(end) && !orderItem.getOrder().getDate().before(start)) {
                buyAmount = buyAmount.add(BigDecimal.valueOf(orderItem.getCount()).multiply(orderItem.getBook().getPrice()));
            }


        });


    }
}
