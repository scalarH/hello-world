package com.singsong.singsong.dao.room.Impl;


import com.singsong.singsong.dao.room.RoomDao;
import com.singsong.singsong.dto.room.Room;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDaoImpl implements RoomDao{

    @Autowired
    SqlSession sqlsession;

	@Override  
	public int writeroomDeatil(Room room) {
		
		return sqlsession.insert("roommapper.writedetail",room);
	}
    

}
