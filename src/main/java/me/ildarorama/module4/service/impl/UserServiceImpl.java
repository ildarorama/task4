package me.ildarorama.module4.service.impl;

import me.ildarorama.module4.model.User;
import me.ildarorama.module4.repository.UserRepository;
import me.ildarorama.module4.service.UserService;
import me.ildarorama.module4.service.dto.UserRequestDto;
import me.ildarorama.module4.service.dto.UserResponseDto;
import me.ildarorama.module4.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto dto) {
        var user = new User();
        user.setBirthday(modelMapper.map(dto.getBirthday(), LocalDate.class));
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        userRepository.save(user);
        return modelMapper.map(user, UserResponseDto.SELF);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto dto) {
        var user = userRepository.findById(dto.getId())
                .orElseThrow(ObjectNotFoundException::new);
        user.setBirthday(modelMapper.map(dto.getBirthday(), LocalDate.class));
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        return modelMapper.map(user, UserResponseDto.SELF);
    }

    @Override
    public void deleteUser(Long id) {
        var user = userRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        userRepository.delete(user);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new);
        return modelMapper.map(user, UserResponseDto.SELF);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return modelMapper.map(users, UserResponseDto.LIST);
    }

    @Override
    public Page<UserResponseDto> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(u -> modelMapper.map(u, UserResponseDto.SELF));
    }
}