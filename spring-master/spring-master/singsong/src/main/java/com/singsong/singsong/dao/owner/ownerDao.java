package com.singsong.singsong.dao.owner;

import java.util.List;

import com.singsong.singsong.dto.Owner.Owner;

public interface ownerDao {
    List<Owner> allOwner();
    Owner getOwner(String id);
}
