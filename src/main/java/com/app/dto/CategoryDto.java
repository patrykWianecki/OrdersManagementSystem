package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Patryk Wianecki
 * @version 1.0
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDto {
    private Long id;
    private String name;
}
