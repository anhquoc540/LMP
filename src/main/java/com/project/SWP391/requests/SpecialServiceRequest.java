package com.project.SWP391.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpecialServiceRequest {


    private String storeId;
    private String name;
    private String description;

    private String image;

    private float price;

    private String unit;

    private List<Long> materials;

    private String clotheId;


}
