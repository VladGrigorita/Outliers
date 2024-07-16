package com.example.demo.util;

import com.example.demo.model.StockData;

import java.util.List;
import java.util.stream.Collectors;

public class OutlierDetector {
    public static List<StockData> detectOutliers(List<StockData> dataPoints) {
        double mean = dataPoints.stream().mapToDouble(StockData::getStockPrice).average().orElse(0.0);
        double stdDev = Math.sqrt(dataPoints.stream()
                .mapToDouble(p -> Math.pow(p.getStockPrice() - mean, 2))
                .average().orElse(0.0));
        double threshold = 2 * stdDev;

        return dataPoints.stream()
                .filter(p -> Math.abs(p.getStockPrice() - mean) > threshold)
                .collect(Collectors.toList());
    }
}
