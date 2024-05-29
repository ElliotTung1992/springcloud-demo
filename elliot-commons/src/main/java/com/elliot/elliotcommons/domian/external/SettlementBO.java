package com.elliot.elliotcommons.domian.external;

import java.io.Serializable;

public class SettlementBO implements Serializable {

    private String orderId;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "SettlementBO{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
