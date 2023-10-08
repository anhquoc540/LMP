package com.project.SWP391.payload;

import com.project.SWP391.entities.Feedback;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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
