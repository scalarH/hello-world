package com.singsong.singsong.dao.profit;

import java.util.List;

import com.singsong.singsong.dto.profit.Profit;

public interface ProfitDao {
    int writeProfit(Profit profit);
    List<Profit> getList(int id);
}
