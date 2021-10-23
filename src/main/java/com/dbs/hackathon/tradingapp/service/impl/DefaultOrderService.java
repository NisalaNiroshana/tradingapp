package com.dbs.hackathon.tradingapp.service.impl;

import com.dbs.hackathon.tradingapp.mockdata.FileUtils;
import com.dbs.hackathon.tradingapp.mockdata.TradeData;
import com.dbs.hackathon.tradingapp.model.FilteredData;
import com.dbs.hackathon.tradingapp.model.Order;
import com.dbs.hackathon.tradingapp.model.OrderResponse;
import com.dbs.hackathon.tradingapp.service.OrderService;
import com.fasterxml.jackson.databind.MappingIterator;
import jdk.nashorn.internal.ir.WhileNode;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class DefaultOrderService implements OrderService {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
    @Override
    public OrderResponse execute(Order order) throws IOException {

        MappingIterator<TradeData> tradeDataI = FileUtils.parseCSVFile("src/main/resources/data-stock/5min/" + order.getTickerId() + ".txt");
        List<TradeData> tradeData =  tradeDataI.readAll();
        Date today = new Date();

        List<FilteredData> filteredData = new ArrayList<>();
        double avgTWAP = 0;

        for (int i = tradeData.size() - 1; filteredData.size() < 10; i--) {
            TradeData temp = tradeData.get(i);
            try {
                Date date = formatter.parse(temp.getDate());

                if(today.compareTo(date) > 0) {
                    FilteredData fil = new FilteredData();

                    fil.setDailyAverage((temp.getClose() + temp.getOpen() + temp.getHigh() + temp.getLow()) /4);
                    avgTWAP = avgTWAP + (temp.getClose() + temp.getOpen() + temp.getHigh() + temp.getLow()) /4;
                    filteredData.add(fil);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
            ;
        }

        avgTWAP = avgTWAP/10;

        double orderValue = order.getQuantity() * order.getPrice();
        OrderResponse orderResponse = new OrderResponse();
        if(orderValue <= avgTWAP) {
            // undervalue -- good for buy
            if(order.getSide() == 0) {
                // status 0
                orderResponse.setStatusId(0);
            } else {
                orderResponse.setStatusId(1);
                // status 1
            }
        } else {
            // over value -- good for sell
            if(order.getSide() == 0) {
                // status 1
                orderResponse.setStatusId(1);
            } else {
                orderResponse.setStatusId(0);
                // status 0
            }
        }

        return orderResponse;
    }
}
