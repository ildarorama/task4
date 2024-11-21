package me.ildarorama.module4.service;

import me.ildarorama.module4.service.dto.UserRequestDto;
import me.ildarorama.module4.service.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);

    UserResponseDto updateUser(UserRequestDto dto);

    void deleteUser(Long id);

    UserResponseDto getUserById(Long id);

    List<UserResponseDto> getAllUsers();

    Page<UserResponseDto> getAllUsers(Pageable pageable);
}
