package com.dbs.hackathon.tradingapp.service;

import com.dbs.hackathon.tradingapp.model.Order;
import com.dbs.hackathon.tradingapp.model.OrderResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface OrderService {
    public OrderResponse execute(Order order) throws IOException;
}
