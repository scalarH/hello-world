package com.singsong.singsong.service.profit;

import java.util.List;

import com.singsong.singsong.dto.profit.Profit;

public interface profitService {
    int writeProfit(Profit profit);
    List<Profit> getList(int id);
}
