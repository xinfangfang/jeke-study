package com.yangchd.week08.homework02.models;

import lombok.Data;

@Data
public class Order {

    private Long orderId;
    private Long uId;

    public Order(long userId, Long orderId) {
        this.order_id = orderId;
        this.u_id = uId;
    }
}
