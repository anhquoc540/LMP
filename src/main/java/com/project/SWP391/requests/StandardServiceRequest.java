package com.project.SWP391.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.SWP391.entities.PriceBasedWeight;
import com.project.SWP391.responses.dto.PriceInWeightDTO;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StandardServiceRequest {
    @JsonIgnore
    private Long id;
    private String name;
    private String description;
    private Set<PriceBasedWeight> prices_weight;


}
