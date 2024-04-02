package com.example.medimateserver.fillter;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class OrderFillter {
    private Integer date;
    private Date minDate;
    private Date maxDate;
}
