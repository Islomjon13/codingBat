package com.example.lesson_1_task2_coding_bat.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class SampleAnswersDto implements Serializable {
    private final String sample;
    private final String answer;
    private final Long problemId;
}
