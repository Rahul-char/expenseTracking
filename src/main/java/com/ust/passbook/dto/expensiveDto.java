package com.ust.passbook.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class expensiveDto {

    private LocalDate date;
    @Pattern(regexp = "^(Dr|Cr)$")
    private String drCr;
    private String creditedFrom;
    private String paymentMode;
    private String dataName;
    @Min(value = 1, message = "Amount should be greater than 0")
    private int amount;
}
