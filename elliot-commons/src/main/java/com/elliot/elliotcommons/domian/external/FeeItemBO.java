package com.elliot.elliotcommons.domian.external;

import java.io.Serializable;
import java.math.BigDecimal;

public class FeeItemBO implements Serializable {

    private Integer id;

    private String orderId;

    private String feeItemName;

    private BigDecimal price;

    private BigDecimal quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    @Override
    public String toString() {
        return "FeeItemBO{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", feeItemName='" + feeItemName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
