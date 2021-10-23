package com.dbs.hackathon.tradingapp.mockdata;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonPropertyOrder({
        "ticker", "per", "date", "time", "open", "high", "low", "close", "vol",
        "openInt"
})
public class TradeData {
    String ticker;
    int per;
    String date;
    String time;
    double open;
    double high;
    double low;
    double close;
    long vol;
    String openInt;
}
