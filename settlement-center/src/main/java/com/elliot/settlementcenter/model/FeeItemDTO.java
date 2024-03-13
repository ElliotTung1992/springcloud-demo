package com.elliot.settlementcenter.model;

import java.math.BigDecimal;

public class FeeItemDTO {

    private String orderId;

    private String id;

    private String feeItemId;

    private String feeItemName;

    private BigDecimal price;

    private BigDecimal quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeeItemName() {
        return feeItemName;
    }

    public void setFeeItemName(String feeItemName) {
        this.feeItemName = feeItemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFeeItemId() {
        return feeItemId;
    }

    public void setFeeItemId(String feeItemId) {
        this.feeItemId = feeItemId;
    }

    @Override
    public String toString() {
        return "FeeItemDTO{" +
                "orderId='" + orderId + '\'' +
                ", id='" + id + '\'' +
                ", feeItemId='" + feeItemId + '\'' +
                ", feeItemName='" + feeItemName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
