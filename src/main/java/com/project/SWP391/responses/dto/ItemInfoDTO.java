package com.project.SWP391.responses.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemInfoDTO {
    private Long id;
    private float total;
    private float weight;
    private SpecialServiceInfoDTO specialServiceInfoDTO;
    private StandardServiceInfoDTO standardServiceInfoDTO;
}
