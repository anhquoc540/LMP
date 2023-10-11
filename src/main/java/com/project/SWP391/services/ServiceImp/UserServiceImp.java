package com.project.SWP391.services.ServiceImp;

import com.project.SWP391.entities.StandardLaundry;
import com.project.SWP391.entities.User;
import com.project.SWP391.repositories.UserRepository;
import com.project.SWP391.responses.dto.StandardServiceInfoDTO;
import com.project.SWP391.responses.dto.UserInfoDTO;
import com.project.SWP391.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {


    private final UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<UserInfoDTO> getAllUsers() {
        var users = userRepository.findAll();
        return users.stream().map(user -> mapToDTO(user)).collect(Collectors.toList());
    }

    @Override
    public UserInfoDTO getUser(Long id) {
        return null;
    }

    @Override
    public UserInfoDTO updateUser(UserInfoDTO request, Long id) {
        return null;
    }

    @Override
    public UserInfoDTO deleteUser(Long id) {
        return null;
    }

    private UserInfoDTO mapToDTO(User dto) {
        return mapper.map(dto, UserInfoDTO.class);
    }

}
