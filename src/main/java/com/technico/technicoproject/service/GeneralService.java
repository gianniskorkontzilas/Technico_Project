package com.technico.technicoproject.service;

import com.technico.technicoproject.dto.OwnerDto;
import com.technico.technicoproject.dto.ResponseResult;
import com.technico.technicoproject.model.Owner;

import java.util.List;

public interface GeneralService {
    ResponseResult<List<OwnerDto>> showAll();
    ResponseResult<Boolean> deleteAll();
}
