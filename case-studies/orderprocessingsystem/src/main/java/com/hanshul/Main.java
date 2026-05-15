package com.hanshul;

import com.hanshul.enums.PaymentMethod;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Order order = new Order(UUID.randomUUID().toString(),5999.9, PaymentMethod.CREDIT_CARD);
        System.out.println(order.getOrderInfo());
        System.out.println("Order Status : "+order.getOrderStatus());
        System.out.println("Order Status Advanced : "+order.advanceStatus());
        System.out.println("Order Status : "+order.getOrderStatus());
    }
}
