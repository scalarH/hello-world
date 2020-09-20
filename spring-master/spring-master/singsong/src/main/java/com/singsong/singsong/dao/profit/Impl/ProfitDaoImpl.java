package com.singsong.singsong.dao.profit.Impl;

import java.util.List;

import com.singsong.singsong.dao.profit.ProfitDao;
import com.singsong.singsong.dto.profit.Profit;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProfitDaoImpl implements ProfitDao {

    @Autowired
    SqlSession sqlsession;

    @Override
    public int writeProfit(Profit profit) {
        return sqlsession.insert("profitmapper.writeprofit", profit);
    }

    @Override
    public List<Profit> getList(int id) {
        return sqlsession.selectList("profitmapper.getList",id);
    }
    
}
