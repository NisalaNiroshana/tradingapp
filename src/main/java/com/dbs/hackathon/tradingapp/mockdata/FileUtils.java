package com.dbs.hackathon.tradingapp.mockdata;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class FileUtils {

    public static void main(String[] args) throws IOException {
        MappingIterator<TradeData> itr = parseCSVFile("src/main/resources/data-stock/5min" +
                "/700.hk.txt");
        System.out.println(itr);
    }

    public static MappingIterator<TradeData> parseCSVFile(String location) throws IOException {
        Path path = Paths.get(location);
        File csvFile = new File(path.toString());
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.enable(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE);
        CsvSchema orderSchema = csvMapper.schemaFor(TradeData.class).withHeader();
        return csvMapper.readerFor(TradeData.class)
                .with(orderSchema).readValues(csvFile);
    }
}
