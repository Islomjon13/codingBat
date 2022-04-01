package com.example.lesson_1_task2_coding_bat.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class LanguageDto implements Serializable {

    private final String name;
}
