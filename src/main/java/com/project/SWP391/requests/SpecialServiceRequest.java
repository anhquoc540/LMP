package com.project.SWP391.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.SWP391.responses.dto.LaundryDetailInfoDTO;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpecialServiceRequest {




    private String name;
    private String description;

    private String imageBanner;
    private List<Long> materials;

    private float price;

    private String unit;

    private Long cloth;

    private int isDeleted;


}
