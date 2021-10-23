package com.dbs.hackathon.tradingapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    long quantity;
    double price;
    String tickerId;
    int side;
}
