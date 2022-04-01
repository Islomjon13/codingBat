package com.example.lesson_1_task2_coding_bat.repository;

import com.example.lesson_1_task2_coding_bat.entity.UsersProblem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersProblemRepository extends JpaRepository<UsersProblem, Long> {
}