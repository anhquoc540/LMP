package com.project.SWP391.responses.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpecialServiceInfoDTO {
    private Long id;
    private String name;

    private String imageBanner;
    private String description;

    private float price;

    private String unit;

    private Set<MaterialDTO> materials;
    private ClothDTO cloth;

    private List<FeedbackDTO> feedbacks;

}
