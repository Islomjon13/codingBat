package com.example.lesson_1_task2_coding_bat.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class TopicDto implements Serializable {
    private final Long languageId;
    private final Integer ordinal;
    private final String name;
    private final String definition;
    private final Integer starNumbers;
}
