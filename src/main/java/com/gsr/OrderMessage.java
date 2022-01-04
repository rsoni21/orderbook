package com.gsr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;

@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)

public class OrderMessage {
    private String type;
    private String product_id;
    private Timestamp time;
    private String message;
    private String reason;
    List<OrderItem> bids;
    List<OrderItem> asks;
    List<OrderUpdate> changes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public List<OrderItem> getBids() {
        return bids;
    }

    public void setBids(List<OrderItem> bids) {
        this.bids = bids;
    }

    public List<OrderItem> getAsks() {
        return asks;
    }

    public void setAsks(List<OrderItem> asks) {
        this.asks = asks;
    }

    public List<OrderUpdate> getChanges() {
        return changes;
    }

    public void setChanges(List<OrderUpdate> changes) {
        this.changes = changes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}


