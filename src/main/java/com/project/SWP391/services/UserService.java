package com.project.SWP391.services;

import com.project.SWP391.responses.dto.UserInfoDTO;

import java.util.List;

public interface UserService {

    List<UserInfoDTO> getAllUsers();
    UserInfoDTO getUser(Long id);

    UserInfoDTO updateUser(UserInfoDTO request, Long id);

    UserInfoDTO deleteUser(Long id);
}
