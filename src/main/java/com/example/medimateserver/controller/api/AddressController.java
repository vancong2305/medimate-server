package com.example.medimateserver.controller.api;

import com.example.medimateserver.dto.AddressDto;
import com.example.medimateserver.service.AddressService;
import com.example.medimateserver.util.GsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping
    public ResponseEntity<String> getAddressById(@PathVariable Integer id, HttpServletRequest request) throws JsonProcessingException {
        try {
            List<AddressDto> AddressList = addressService.findAll();
            String jsons = GsonUtil.gI().toJson(AddressList);
            return new ResponseEntity<>(
                    jsons,
                    HttpStatus.OK
            );
        } catch (Exception ex) {
            return new ResponseEntity<>(
                    "badRequest",
                    HttpStatus.BAD_REQUEST
            );
        }

    }

    // Create a new Address
    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto AddressDto) {
        AddressDto savedAddress = addressService.save(AddressDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }

    // Update a Address
    @PutMapping
    public ResponseEntity<String> updateAddress(@PathVariable Integer id, @RequestBody AddressDto Address) {
        return ResponseEntity.ok("");
    }

    // Delete a Address
    @DeleteMapping
    public ResponseEntity<String> deleteAddress(@PathVariable Integer id) {
        try {
            AddressDto Address = addressService.findById(id);
            addressService.save(Address);
            return new ResponseEntity<>(
                    "Success",
                    HttpStatus.OK
            );
        } catch (Exception ex) {

        }
        return ResponseEntity.noContent().build();
    }
}
