package me.ildarorama.module4.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserRequestDto {
    @Schema(title = "User id", example = "1")
    private Long id;
    @Schema(title = "User name", example = "John")
    private String name;
    @Schema(title = "User surname", example = "Doe")
    private String surname;
    @Schema(title = "User birthday", example = "2022-10-14")
    private String birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
