package com.app.dto;

import com.app.model.EGuarantee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuaranteeComponentDto {
    private Long id;
    private EGuarantee guaranteeComponent;
    private ProductDto productDto;
}
