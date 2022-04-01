package com.example.lesson_1_task2_coding_bat.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsersDto implements Serializable {
    private final String email;
    private final String password;
}
