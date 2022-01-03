# orderbook

This is an order book app with order book view of 10 levels

It is reading market data from [Coinbase Pro API- Level 2 channel](https://docs.cloud.coinbase.com/exchange/docs/channels#the-level2-channel)

Steps to start application

1. clone repo
2. mvn clean install
3. Run application from java file OrderBookApp.java (pass product ID as argument)

Note: If running from terminal, use following command
> java -classpath target/classes:<i><.m2 path to all jars></i> com.gsr.OrderBookApp <i><product_id></i>

