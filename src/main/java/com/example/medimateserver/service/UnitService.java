package com.example.medimateserver.service;

import com.example.medimateserver.dto.UnitDto;

import java.util.List;

public interface UnitService {
    List<UnitDto> findAll();
    List<UnitDto> findWithPageAndSize(int page, int size);
    UnitDto findById(Integer id);

    UnitDto save(UnitDto UnitDto);

    UnitDto update(Integer id, UnitDto UnitDto);

    void deleteById(Integer id);
}
