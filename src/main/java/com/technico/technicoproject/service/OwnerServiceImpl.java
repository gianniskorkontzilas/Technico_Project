package com.technico.technicoproject.service;

import com.technico.technicoproject.dto.OwnerDto;
import com.technico.technicoproject.exception.OwnerException;
import com.technico.technicoproject.model.Owner;
import com.technico.technicoproject.repository.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService {
    private OwnerRepository ownerRepository;

    @Override
    public Owner createOwner(Owner owner) throws OwnerException {

        boolean exists = ownerRepository.existsOwnersByVatNumber(owner.getVatNumber());
        if (exists) {
            throw new OwnerException("The Vat Number already exists");
        } else {
            String regexPattern = "^(.+)@(\\S+)$";
            if (owner.getVatNumber().length() == 9 && Pattern.compile(regexPattern).matcher(owner.getEmail()).matches() && owner.getPhoneNumber().length() == 10)
                return ownerRepository.save(owner);
            else {

                throw new OwnerException("Invalid data");
            }
        }
    }

    @Override
    public List<OwnerDto> readOwners() {
        return ownerRepository.findAll().stream().map(ownerdto -> new OwnerDto(
                ownerdto.getVatNumber(),
                ownerdto.getFirstName(),
                ownerdto.getLastName(),
                ownerdto.getAddress(),
                ownerdto.getPhoneNumber(),
                ownerdto.getEmail(),
                ownerdto.getUsername())).toList();
    }

    @Override
    public OwnerDto readOwnerByVatNumber(String ownerVatNumber) {
        OwnerDto ownerDto = new OwnerDto();
        Owner owner = ownerRepository.findOwnerByVatNumber(ownerVatNumber);
        ownerDto.setFirstName(owner.getFirstName());
        ownerDto.setEmail(owner.getEmail());
        ownerDto.setAddress(owner.getAddress());
        ownerDto.setUsername(owner.getUsername());
        ownerDto.setLastName(owner.getLastName());
        ownerDto.setPhoneNumber(owner.getPhoneNumber());
        ownerDto.setVatNumber(owner.getVatNumber());
        return ownerDto;
    }

    @Override
    public OwnerDto readOwnerByEmail(String ownerEmail) {
        OwnerDto ownerDto = new OwnerDto();
        Owner owner = ownerRepository.findOwnerByEmail(ownerEmail);
        ownerDto.setFirstName(owner.getFirstName());
        ownerDto.setEmail(owner.getEmail());
        ownerDto.setAddress(owner.getAddress());
        ownerDto.setUsername(owner.getUsername());
        ownerDto.setLastName(owner.getLastName());
        ownerDto.setPhoneNumber(owner.getPhoneNumber());
        ownerDto.setVatNumber(owner.getVatNumber());
        return ownerDto;
    }

    @Override
    public Owner updateOwner(String vatNumber, Owner updateOwner) {

        Optional<Owner> owner = ownerRepository.findById(vatNumber);
        if (owner.isPresent()) {
            Owner owner1 = owner.get();
            owner1.setAddress(updateOwner.getAddress());
            owner1.setEmail(updateOwner.getEmail());
            owner1.setFirstName(updateOwner.getFirstName());
            owner1.setLastName(updateOwner.getLastName());
            owner1.setPassword(updateOwner.getPassword());
            owner1.setPhoneNumber(updateOwner.getPhoneNumber());
            owner1.setVatNumber(updateOwner.getVatNumber());
            owner1.setUsername(updateOwner.getUsername());
            return ownerRepository.save(owner1);
        } else {
            return null;
        }

    }

    @Override
    public boolean deleteOwner(String vatNumber) {
        ownerRepository.deleteById(vatNumber);
        return true;
    }
}