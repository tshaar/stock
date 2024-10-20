package com.stock.exchange.stock.model;


public class StockPurchaseDTO {

    private String ticker; // Ticker to identify the stock
    private String buySide; // Buy or sell side
    private int boughtQuantity; // Quantity purchased

    // Getters and setters
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getBuySide() {
        return buySide;
    }

    public void setBuySide(String buySide) {
        this.buySide = buySide;
    }

    public int getBoughtQuantity() {
        return boughtQuantity;
    }

    public void setBoughtQuantity(int boughtQuantity) {
        this.boughtQuantity = boughtQuantity;
    }
}
