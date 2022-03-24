package com.technico.technicoproject.controller;

import com.technico.technicoproject.dto.OwnerDto;
import com.technico.technicoproject.dto.ResponseResult;
import com.technico.technicoproject.service.GeneralService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class GeneralController {
    private GeneralService generalService;
    @GetMapping("/all")
    public ResponseResult<List<OwnerDto>> showAll(){
        return generalService.showAll();
    }

    @DeleteMapping("/all")
    public ResponseResult<Boolean> deleteAll(){
        return generalService.deleteAll();
    }
}


