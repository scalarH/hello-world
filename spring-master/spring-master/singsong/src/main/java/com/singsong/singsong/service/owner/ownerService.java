package com.singsong.singsong.service.owner;

import java.util.List;

import com.singsong.singsong.dto.Owner.Owner;

public interface ownerService {
    List<Owner> allOwner();
    Owner getOwner(String id);
}
