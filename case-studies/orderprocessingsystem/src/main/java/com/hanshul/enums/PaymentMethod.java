package com.hanshul.enums;

public enum PaymentMethod {

    CREDIT_CARD("Credit Card", 5.99),
    DEBIT_CARD("Debit Card", 2.99),
    NET_BANKING("Net Banking", 2.00),
    UPI("Unified Payment Interface", 0.00);

    private final String displayName;
    private final Double feePercentage;

    PaymentMethod(String displayName, Double feePercentage) {
        this.displayName = displayName;
        this.feePercentage = feePercentage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Double getFeePercentage() {
        return feePercentage;
    }
}
