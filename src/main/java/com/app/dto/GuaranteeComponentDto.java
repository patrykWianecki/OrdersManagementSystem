package com.app.dto;

import com.app.model.EGuarantee;
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
public class GuaranteeComponentDto {
    private Long id;
    private EGuarantee guaranteeComponent;
    private ProductDto productDto;
}
