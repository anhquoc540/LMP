package com.project.SWP391.responses.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LaundryDetailInfoDTO {
    private Long id;
    private String name;
    private String description;
    private Set<PriceInWeightDTO> prices_weight;
}
