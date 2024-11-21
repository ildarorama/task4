package me.ildarorama.module4.controller.v2;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.ildarorama.module4.service.UserService;
import me.ildarorama.module4.service.dto.UserRequestDto;
import me.ildarorama.module4.service.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User operation")
@RestController
@RequestMapping("/api/v2/")
public class UserControllerV2 {
    private final UserService userService;

    @Autowired
    public UserControllerV2(UserService userService) {
        this.userService = userService;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User created")
    })
    @PostMapping(value = "user", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Create new user")
    public UserResponseDto createUser(UserRequestDto dto) {
        return userService.createUser(dto);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "No user with id")
    })
    @PatchMapping(value = "user", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Update particular user")
    public UserResponseDto updateUser(UserRequestDto dto) {
        return userService.updateUser(dto);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "No user with id")
    })
    @DeleteMapping(value = "user/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Delete user by id")
    public void deleteUser(@Schema(description = "User id") @PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User info"),
            @ApiResponse(responseCode = "404", description = "No user with id")
    })
    @Operation(description = "Get user by id")
    @GetMapping(value = "/user/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public UserResponseDto getUser(@Schema(description = "User id") @PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of users")
    })
    @Operation(description = "Get all users")
    @GetMapping(value = "user/all", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Page<UserResponseDto> getAllUser(@SortDefault(sort = "id") @PageableDefault(size = 20) final Pageable pageable) {
        return userService.getAllUsers(pageable);
    }
}
