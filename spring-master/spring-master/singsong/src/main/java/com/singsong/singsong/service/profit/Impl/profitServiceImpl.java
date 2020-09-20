package com.singsong.singsong.service.profit.Impl;

import java.util.List;

import com.singsong.singsong.dao.profit.ProfitDao;
import com.singsong.singsong.dto.profit.Profit;
import com.singsong.singsong.service.profit.profitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class profitServiceImpl implements profitService {

    @Autowired
    ProfitDao dao;

    @Override
    public int writeProfit(Profit profit) {
        return dao.writeProfit(profit);
    }

    @Override
    public List<Profit> getList(int id) {
        return dao.getList(id);
    }
    
}
