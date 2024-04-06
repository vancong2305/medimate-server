package com.example.medimateserver.service.impl;

import com.example.medimateserver.dto.AddressDto;
import com.example.medimateserver.entity.Address;
import com.example.medimateserver.repository.AddressRepository;
import com.example.medimateserver.service.AddressService;
import com.example.medimateserver.util.ConvertUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    @Override
    public List<AddressDto> findAll() {
        List<Address> AddressList = addressRepository.findAll();
        return AddressList
                .stream()
                .map(Address -> ConvertUtil.gI().toDto(Address, AddressDto.class))
                .collect(Collectors.toList());
    }
    public List<AddressDto> findWithPageAndSize(int page, int size) {
        // Tạo Pageable object
        Pageable pageable = PageRequest.of(page, size);
        // Truy vấn dữ liệu
        List<Address> Addresss = (List<Address>) addressRepository.findAll(pageable);
        return Addresss
                .stream()
                .map(Address -> ConvertUtil.gI().toDto(Address, AddressDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {

        return addressRepository.findById(id)
                .map(Address -> ConvertUtil.gI().toDto(Address, AddressDto.class))
                .orElse(null);
    }
    @Override
    public AddressDto save(AddressDto AddressDto) {
        Address Address = ConvertUtil.gI().toEntity(AddressDto, Address.class);
        Address = addressRepository.save(Address);
        return ConvertUtil.gI().toDto(Address, AddressDto.class);
    }

    @Override
    public AddressDto update(Integer id, AddressDto AddressDto) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
        Address updatedCategory = addressRepository.save(existingAddress);
        return ConvertUtil.gI().toDto(updatedCategory, AddressDto.class);
    }

    @Override
    public void deleteById(Integer id) {

    }
}
