package com.singsong.singsong.service.owner.Impl;

import java.util.List;

import com.singsong.singsong.dao.owner.ownerDao;
import com.singsong.singsong.dto.Owner.Owner;
import com.singsong.singsong.service.owner.ownerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ownerServiceImple implements ownerService {

    @Autowired
    ownerDao ownerdao;

    @Override
    public List<Owner> allOwner() {
        return ownerdao.allOwner();
    }

    @Override
    public Owner getOwner(String id) {
        return ownerdao.getOwner(id);
    }
    
}
