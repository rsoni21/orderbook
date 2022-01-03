package com.gsr;

import java.math.BigDecimal;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/*
 * Maintain the state of the order book
 */
public class OrderBook {
    public static final String BUY = "buy";
    private static OrderBook orderBook ;

    PriorityQueue<OrderItem> buyOrders = new PriorityQueue<>((a, b)-> b.getPrice().compareTo(a.getPrice()));
    PriorityQueue<OrderItem> sellOrders = new PriorityQueue<>((a, b)-> a.getPrice().compareTo(b.getPrice()));

    private OrderBook(){}

    public static OrderBook getInstance(){
        if(orderBook == null){
            orderBook = new OrderBook();
        }
        return orderBook;
    }

    void addSellOrders(List<OrderItem> asks){
        if(asks==null || asks.isEmpty())
            return;
        sellOrders.addAll(asks);
    }

    void addBuyOrders(List<OrderItem> bids){
        if(bids==null || bids.isEmpty())
            return;
        buyOrders.addAll(bids);
    }

    void updateOrderBook(OrderUpdate trade){
        if(trade.getSide().equals(BUY))
            buyOrders.add(new OrderItem(trade.getPrice(), trade.getSize()));
        else
            sellOrders.add(new OrderItem(trade.getPrice(), trade.getSize()));

        removeZeroSizeOrders(buyOrders, sellOrders);

        while(!sellOrders.isEmpty() && !buyOrders.isEmpty() && sellOrders.peek().getPrice().compareTo(buyOrders.peek().getPrice()) <=0 ){
            BigDecimal diff = sellOrders.peek().getSize().min(buyOrders.peek().getSize());
            sellOrders.peek().setSize(sellOrders.peek().getSize().subtract(diff));
            buyOrders.peek().setSize(buyOrders.peek().getSize().subtract(diff));
            removeZeroSizeOrders(buyOrders, sellOrders);
        }

    }

    private void removeZeroSizeOrders(PriorityQueue<OrderItem> buyOrders, PriorityQueue<OrderItem> sellOrders) {
        while(buyOrders.peek().getSize().compareTo(BigDecimal.ZERO) == 0) buyOrders.poll();
        while(sellOrders.peek().getSize().compareTo(BigDecimal.ZERO) == 0) sellOrders.poll();
    }

    void displayOrderBook(int numElements){
        List<OrderItem> topSells = sellOrders.stream().collect(Collectors.toList()).subList(0, Math.min(sellOrders.size(), numElements));
        List<OrderItem> topBuys = buyOrders.stream().collect(Collectors.toList()).subList(0, Math.min(buyOrders.size(), numElements));

        System.out.flush();

        System.out.println("BUYS: Price | Size" + "\t" + "SELLS: Price | Size");
        for(int i=0; i<Math.min(topBuys.size(), Math.min(topSells.size(), numElements)); i++){
            System.out.println(topBuys.get(i).toString()+ "\t" + topSells.get(i).toString());
        }
        System.out.println("--------------------------------------------------");
    }

}
