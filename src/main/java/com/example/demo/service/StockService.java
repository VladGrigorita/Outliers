package com.example.demo.service;

import com.example.demo.model.StockData;
import com.example.demo.util.CsvUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class StockService {
    public List<List<StockData>> getRandom30DataPoints(String directoryPath, int numberOfFiles) {
        List<List<StockData>> sampledData = new ArrayList<>();
        try {
            List<String> filePaths = Files.list(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .limit(numberOfFiles)
                    .toList();

            for (String filePath : filePaths) {
                List<StockData> allData = CsvUtil.readStockData(filePath);
                if (allData.size() < 30) {
                    sampledData.add(Collections.emptyList());
                    continue;
                }
                int maxStartIndex = allData.size() - 30;
                int startIndex = new Random().nextInt(maxStartIndex + 1);
                sampledData.add(allData.subList(startIndex, startIndex + 30));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sampledData;
    }
}
