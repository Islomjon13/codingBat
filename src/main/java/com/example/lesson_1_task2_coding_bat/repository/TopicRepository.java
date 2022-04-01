package com.example.lesson_1_task2_coding_bat.repository;

import com.example.lesson_1_task2_coding_bat.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}