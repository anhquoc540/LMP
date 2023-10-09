package com.project.SWP391.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private String image;

    private float price;

    private String unit;

    private List<Long> materials;

    private Long clothId;
    private Long storeId;
    private int isDeleted;


}
