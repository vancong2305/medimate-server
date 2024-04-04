package com.example.medimateserver.service;

import com.example.medimateserver.dto.UnitDetailDto;

import java.util.List;

public interface UnitDetailService {
    List<UnitDetailDto> findAll();
    UnitDetailDto findById(Integer id);
    UnitDetailDto save(UnitDetailDto unitDetailDto);
    UnitDetailDto update(Integer id, UnitDetailDto unitDetailDto);
    void deleteById(Integer id);
}
