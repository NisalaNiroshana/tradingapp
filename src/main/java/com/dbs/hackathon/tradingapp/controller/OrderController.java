package com.dbs.hackathon.tradingapp.controller;

import com.dbs.hackathon.tradingapp.model.Order;
import com.dbs.hackathon.tradingapp.model.OrderResponse;
import com.dbs.hackathon.tradingapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "tradeapp")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "order/execute", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderResponse storeSalesInfo(@RequestBody Order order) throws IOException {
        return orderService.execute(order);
    }
}
