package com.technico.technicoproject.controller;

import com.technico.technicoproject.dto.OwnerDto;
import com.technico.technicoproject.dto.ResponseResult;
import com.technico.technicoproject.dto.ResponseStatus;
import com.technico.technicoproject.model.Owner;
import com.technico.technicoproject.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/owner")
public class OwnerController {
    private OwnerService ownerService;
    @PostMapping("/create")
    public ResponseResult<Owner> create(@RequestBody Owner owner) throws Exception {
        return  ownerService.createOwner(owner);
    }
    @GetMapping("/owners")
    public ResponseResult<List<Owner>> findAllOwners(){
        return ownerService.readOwners();
    }

    @GetMapping("/{vatNumber}")
    public ResponseResult<Owner> findByVat(@PathVariable ("vatNumber") String vatNumber){
        return ownerService.readOwnerByVatNumber(vatNumber);
    }

    @GetMapping("/email/{ownerEmail}")
    public ResponseResult<Owner> findByEmail(@PathVariable ("ownerEmail") String ownerEmail){
        return ownerService.readOwnerByEmail(ownerEmail);
    }

    @PutMapping("/{vatNumber}")
    public ResponseResult<Owner> update(@PathVariable("vatNumber")String vatNumber , @RequestBody Owner owner){
        return ownerService.updateOwner(vatNumber,owner);
    }

    @DeleteMapping("/{vatNumber}")
    public ResponseResult<Boolean> delete(@PathVariable("vatNumber")String vatNumber){
        return ownerService.deleteOwner(vatNumber);
    }
    @DeleteMapping("")
    public ResponseResult<Boolean> delete(){
        return ownerService.deleteAllOwners();
    }

}

