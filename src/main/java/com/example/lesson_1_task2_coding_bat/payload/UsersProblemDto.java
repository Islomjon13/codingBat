package com.example.lesson_1_task2_coding_bat.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsersProblemDto implements Serializable {
    private final Long userId;
    private final Long problemId;
    private final String user_answer;
}
