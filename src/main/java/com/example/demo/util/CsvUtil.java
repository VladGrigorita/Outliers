package com.example.demo.util;

import com.example.demo.model.StockData;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class CsvUtil {
    public static List<StockData> readStockData(String filePath) throws IOException {
        List<StockData> stockDataList = new ArrayList<>();
        Path path = Paths.get(filePath);
        try (Reader reader = Files.newBufferedReader(path);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                StockData stockData = new StockData(
                        csvRecord.get(0),
                        csvRecord.get(1),
                        Double.parseDouble(csvRecord.get(2))
                );
                stockDataList.add(stockData);
            }
        }
        return stockDataList;
    }
    public static void writeOutliersToCsv(String outputPath, List<StockData> outliers) throws IOException {
        try (Writer writer = Files.newBufferedWriter(Paths.get(outputPath));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader("Stock-ID", "Timestamp", "Stock Price", "Mean", "Deviation", "Percentage Deviation"))) {

            double mean = outliers.stream().mapToDouble(StockData::getStockPrice).average().orElse(0.0);
            for (StockData outlier : outliers) {
                double deviation = outlier.getStockPrice() - mean;
                double percentageDeviation = (deviation / mean) * 100;
                csvPrinter.printRecord(outlier.getStockId(), outlier.getTimestamp(), outlier.getStockPrice(),
                        mean, deviation, percentageDeviation);
            }
        }
    }
}
