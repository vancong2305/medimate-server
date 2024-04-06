package com.example.medimateserver.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {
    private Integer id;
    private String phone;
    private String ward;
    private String district;
    private String province;
    private String type;
    private boolean isDefault;
    private Integer status;
}
