package com.gsr;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.util.List;

public class OrderItem {
    BigDecimal price;
    BigDecimal size;

    public OrderItem(BigDecimal price, BigDecimal size) {
        this.price = price;
        this.size = size;
    }

    @JsonCreator
    public OrderItem(List<String> orders){
        if(orders==null || orders.size() < 2){
            throw new IllegalArgumentException("Changes from API in incorrect format. Check connection");
        }
        price = new BigDecimal(orders.get(0));
        size = new BigDecimal(orders.get(1));
    }

    @Override
    public String toString(){
        return price.toString()+"\t"+size.toString();
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
