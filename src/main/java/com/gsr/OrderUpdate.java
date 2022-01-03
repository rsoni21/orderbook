package com.gsr;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;
import java.util.List;

public class OrderUpdate {
    String side;
    BigDecimal price;
    BigDecimal size;

    @JsonCreator
    public OrderUpdate(List<String> orders){
        if(orders==null || orders.size() < 3){
            throw new IllegalArgumentException("Changes from API in incorrect format. Check connection");
        }
        side = orders.get(0);
        price = new BigDecimal(orders.get(1));
        size = new BigDecimal(orders.get(2));
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }
}
