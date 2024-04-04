package com.example.medimateserver.service.impl;

import com.example.medimateserver.dto.UnitDetailDto;
import com.example.medimateserver.entity.UnitDetail;
import com.example.medimateserver.repository.UnitDetailRepository;
import com.example.medimateserver.service.UnitDetailService;
import com.example.medimateserver.util.ConvertUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UnitDetailServiceImpl implements UnitDetailService {

    @Autowired
    UnitDetailRepository UnitDetailRepository;

    @Override
    public List<UnitDetailDto> findAll() {
        List<UnitDetail> categories = UnitDetailRepository.findAll();
        return categories.stream()
                .map(UnitDetail -> ConvertUtil.gI().toDto(UnitDetail, UnitDetailDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public UnitDetailDto findById(Integer id) {
        return UnitDetailRepository.findById(id)
                .map(UnitDetail -> ConvertUtil.gI().toDto(UnitDetail, UnitDetailDto.class))
                .orElse(null); // Trả về null nếu không tìm thấy UnitDetail
    }

    @Override
    public UnitDetailDto save(UnitDetailDto UnitDetailDto) {
        UnitDetail UnitDetail = ConvertUtil.gI().toEntity(UnitDetailDto, UnitDetail.class);
        UnitDetail = UnitDetailRepository.save(UnitDetail);
        return ConvertUtil.gI().toDto(UnitDetail, UnitDetailDto.class);
    }


    @Override
    public UnitDetailDto update(Integer id, UnitDetailDto UnitDetailDto) {
        UnitDetail existingUnitDetail = UnitDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UnitDetail not found with id: " + id));

        // Cập nhật các thuộc tính từ UnitDetailDto sang existingUnitDetail

        UnitDetail updatedUnitDetail = UnitDetailRepository.save(existingUnitDetail);
        return ConvertUtil.gI().toDto(updatedUnitDetail, UnitDetailDto.class);
    }

    @Override
    public void deleteById(Integer id) {
        if (!UnitDetailRepository.existsById(id)) {
            throw new EntityNotFoundException("UnitDetail not found with id: " + id);
        }
        UnitDetailRepository.deleteById(id);
    }
}
