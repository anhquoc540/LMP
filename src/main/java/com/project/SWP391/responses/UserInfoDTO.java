package com.project.SWP391.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserInfoDTO {
    private Long id;
    private String email;
    private String phone;
    private String fullName;
    private String address;
    private int status;
}
