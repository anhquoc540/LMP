package com.project.SWP391.responses.dto;

import com.project.SWP391.entities.User;
import com.project.SWP391.responses.SpecialServiceResponseInItem;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StoreInfoDTO {
    private Long id;
    private String name;
    private String address;
    private String district;
    private String phone;

    private UserInfoDTO user;


}
