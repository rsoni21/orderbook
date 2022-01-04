package com.gsr;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderUpdate extends OrderItem{
    String side;

    @JsonCreator
    public OrderUpdate(List<String> orders){
        super(new ArrayList<>(Arrays.asList(orders.get(1), orders.get(2))));
        if(orders==null || orders.size() < 3){
            throw new IllegalArgumentException("Changes from API in incorrect format. Check connection");
        }
        side = orders.get(0);
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

}
