package com.example.medimateserver.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WardDto {
    private Integer idWard;
    private Integer idDistrict;
    private String name;
    private DistrictDto districtDto;
}
