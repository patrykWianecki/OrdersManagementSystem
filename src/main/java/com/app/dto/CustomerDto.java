package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto {
    private Long id;
    private Integer age;
    private String name;
    private String surname;
    private CountryDto countryDto;
}
