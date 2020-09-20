package com.singsong.singsong.service.room.Impl;


import com.singsong.singsong.dao.room.RoomDao;
import com.singsong.singsong.dto.room.Room;
import com.singsong.singsong.service.room.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	RoomDao roomdao;

	@Override  
	public int writeroomDeatil(Room room) {
		
		return roomdao.writeroomDeatil(room);
	}
    

}
