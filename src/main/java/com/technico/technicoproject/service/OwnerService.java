package com.technico.technicoproject.service;

import com.technico.technicoproject.dto.OwnerDto;
import com.technico.technicoproject.model.Owner;

import java.util.List;

public interface OwnerService {
    Owner createOwner(Owner owner) throws Exception;
    List<OwnerDto> readOwners();
    OwnerDto readOwnerByVatNumber(String ownerVatNumber);
    OwnerDto readOwnerByEmail(String ownerEmail);
    Owner updateOwner(String vatNumber ,Owner owner );
    boolean deleteOwner(String vatNumber);
}
