package com.hanshul;

import com.hanshul.enums.OrderStatus;
import com.hanshul.enums.PaymentMethod;

public class Order {

    private final String orderId;
    private Double amount;
    private OrderStatus status;
    private PaymentMethod paymentMethod;

    Order(String orderId, Double amount, PaymentMethod paymentMethod) {
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status=OrderStatus.PLACED;
    }

    public boolean advanceStatus() {
        switch (status) {
            case PLACED -> {
                status=OrderStatus.CONFIRMED;
                return true;
            }
            case CONFIRMED -> {
                status=OrderStatus.PROCESSING;
                return true;
            }
            case PROCESSING -> {
                status = OrderStatus.SHIPPED;
                return true;
            }
            case SHIPPED -> {
                status = OrderStatus.DELIVERED;
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    public String getOrderStatus() {
        return status.name();
    }

    public String getOrderInfo() {
        return String.format("orderId : %s | status : %s | paymentMethod : %s | amount : ₹%.2f (with fees : ₹%.2f)",orderId,status,paymentMethod.getDisplayName(),amount,calculateOrderAmountWithFees());
    }

    public Double calculateOrderAmountWithFees() {
        return amount + (amount * (paymentMethod.getFeePercentage()/100));
    }

}
