package com.technico.technicoproject.controller;

import com.technico.technicoproject.dto.OwnerDto;
import com.technico.technicoproject.model.Owner;
import com.technico.technicoproject.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/owner/")
public class OwnerController {
    private OwnerService ownerService;
    @PostMapping("create")
    public Owner create(@RequestBody Owner owner) throws Exception {
        return  ownerService.createOwner(owner);
    }
    @GetMapping("owners")
    public List<OwnerDto> findAllOwners(){
        return ownerService.readOwners();
    }

    @GetMapping("{ownerVatNumber}")
    public OwnerDto findByVat(@PathVariable ("ownerVatNumber") String ownerVat){
        return ownerService.readOwnerByVatNumber(ownerVat);
    }

    @GetMapping("email/{ownerEmail}")
    public OwnerDto findByEmail(@PathVariable ("ownerEmail") String ownerEmail){
        return ownerService.readOwnerByEmail(ownerEmail);
    }

    @PutMapping("update/{vatNumber}")
    public Owner update(@PathVariable("vatNumber")String vatNumber , @RequestBody Owner owner){
        return ownerService.updateOwner(vatNumber,owner);
    }

    @DeleteMapping("delete/{vatNumber}")
    public boolean delete(@PathVariable("vatNumber")String vatNumber){
        return ownerService.deleteOwner(vatNumber);
    }

}

