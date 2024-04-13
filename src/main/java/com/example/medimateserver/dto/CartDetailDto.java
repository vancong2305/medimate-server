package com.example.medimateserver.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDetailDto {
    private Integer idUser;
    private Integer idProduct;
    private Integer quantity;
    private UserDto userDto;
    private ProductDto productDto;
}
