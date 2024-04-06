package com.example.medimateserver.service.impl;

import com.example.medimateserver.dto.AddressDto;
import com.example.medimateserver.dto.CouponDetailDto;
import com.example.medimateserver.entity.Address;
import com.example.medimateserver.entity.CouponDetail;
import com.example.medimateserver.repository.AddressRepository;
import com.example.medimateserver.service.AddressService;
import com.example.medimateserver.util.ConvertUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public List<AddressDto> findAll() {
        List<Address> AddressList = addressRepository.findAll();
        return AddressList
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
    public AddressDto save(Integer id, AddressDto addressDto) {
        Address savedAddress = ConvertUtil.gI().toEntity(addressDto, Address.class);
        savedAddress = addressRepository.save(savedAddress);
        if (addressDto.getIsDefault()) {
            System.out.println("Zô đây rồi nè");
            List<Address> addresses = addressRepository.findByIdUser(id);
            for (Address address : addresses) {
                address.setIsDefault(false);
                if (address.getId()==savedAddress.getId()) {
                    address.setIsDefault(true);
                }
            }
            addressRepository.saveAll(addresses);
        }
        return ConvertUtil.gI().toDto(savedAddress, AddressDto.class);
    }

    @Override
    public AddressDto update(Integer id, AddressDto addressDto) {
        Address existingAddress = addressRepository.findById(addressDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("We not found with id: " + addressDto.getId()));
        // Kiểm tra isDefault của addressDto
        if (addressDto.getIsDefault()) {
            System.out.println("Zô đây rồi nè");
            Address updatedAddress = null;
            List<Address> addresses = addressRepository.findByIdUser(id);
            for (Address address : addresses) {
                address.setIsDefault(false);
                if (address.getId().equals(addressDto.getId())) {
                    BeanUtils.copyProperties(addressDto, address);
                    updatedAddress = address;
                }
            }
            addressRepository.saveAll(addresses);
            return ConvertUtil.gI().toDto(updatedAddress, AddressDto.class);
        }
        // Cập nhật dữ liệu cho Address hiện tại (có thể dùng BeanUtils.copyProperties)
        BeanUtils.copyProperties(addressDto, existingAddress);
        Address updatedAddress = addressRepository.save(existingAddress);
        return ConvertUtil.gI().toDto(updatedAddress, AddressDto.class);
    }
    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<AddressDto> findByIdUser(Integer id) {
        List<Address> addressList = addressRepository.findByIdUser(id);
        return addressList.stream()
                .map(address -> ConvertUtil.gI().toDto(address, AddressDto.class))
                .collect(Collectors.toList());
    }
}
