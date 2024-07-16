package com.example.demo.model;

public class StockData {
    private String stockId;
    private String timestamp;
    private double stockPrice;

    public StockData(String stockId, String timestamp, double stockPrice) {
        this.stockId = stockId;
        this.timestamp = timestamp;
        this.stockPrice = stockPrice;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }
}
