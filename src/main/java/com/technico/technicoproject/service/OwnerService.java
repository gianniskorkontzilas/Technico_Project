package com.technico.technicoproject.service;

import com.technico.technicoproject.dto.ResponseResult;
import com.technico.technicoproject.model.Owner;

import java.util.List;

public interface OwnerService {
    ResponseResult<Owner> createOwner(Owner owner) throws Exception;

    ResponseResult<List<Owner>> readOwners();

    ResponseResult<Owner> readOwnerByVatNumber(String ownerVatNumber);

    ResponseResult<Owner> readOwnerByEmail(String ownerEmail);

    ResponseResult<Owner> updateOwner(String vatNumber, Owner owner);

    ResponseResult<Boolean> deleteOwner(String vatNumber);

    ResponseResult<Boolean> deleteAllOwners();
}
