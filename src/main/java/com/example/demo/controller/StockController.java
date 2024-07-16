package com.example.demo.controller;

import com.example.demo.model.StockData;
import com.example.demo.service.StockService;
import com.example.demo.util.CsvUtil;
import com.example.demo.util.OutlierDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/random-data-points")
    public List<List<StockData>> getRandomDataPoints(@RequestParam String directoryPath, @RequestParam int numberOfFiles) {
        return stockService.getRandom30DataPoints(directoryPath, numberOfFiles);
    }

    @GetMapping("/outliers")
    public List<List<StockData>> getOutliers(@RequestParam String directoryPath, @RequestParam int numberOfFiles) throws IOException {
        List<List<StockData>> allOutliers = new ArrayList<>();
        List<List<StockData>> dataPointsList = stockService.getRandom30DataPoints(directoryPath, numberOfFiles);

        for (List<StockData> dataPoints : dataPointsList) {
            List<StockData> outliers = OutlierDetector.detectOutliers(dataPoints);
            allOutliers.add(outliers);
        }

        // Generate CSV files for each list of outliers
        int index = 1;
        for (List<StockData> outliers : allOutliers) {
            String outputPath = directoryPath + "/outliers_" + index + ".csv";
            CsvUtil.writeOutliersToCsv(outputPath, outliers);
            index++;
        }

        return allOutliers;
    }
}
