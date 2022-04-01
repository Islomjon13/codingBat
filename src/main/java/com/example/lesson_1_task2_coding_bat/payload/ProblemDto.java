package com.example.lesson_1_task2_coding_bat.payload;

import lombok.Data;

import java.io.Serializable;

@Data

public class ProblemDto implements Serializable {
    private final Long topicId;
    private final Integer ordinal;
    private final String text;
    private final String default_code;
}
