package com.dbs.hackathon.tradingapp.service.impl;

import com.dbs.hackathon.tradingapp.model.Order;
import com.dbs.hackathon.tradingapp.model.OrderResponse;
import com.dbs.hackathon.tradingapp.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class DefaultOrderService implements OrderService {
    @Override
    public OrderResponse execute(Order order) {
        return null;
    }
}
