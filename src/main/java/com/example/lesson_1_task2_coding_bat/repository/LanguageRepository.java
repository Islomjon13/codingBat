package com.example.lesson_1_task2_coding_bat.repository;

import com.example.lesson_1_task2_coding_bat.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    boolean existsByName(String name);
}