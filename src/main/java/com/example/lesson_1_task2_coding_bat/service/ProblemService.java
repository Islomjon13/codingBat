package com.example.lesson_1_task2_coding_bat.service;

import com.example.lesson_1_task2_coding_bat.entity.Problem;
import com.example.lesson_1_task2_coding_bat.entity.Topic;
import com.example.lesson_1_task2_coding_bat.payload.ApiResponse;
import com.example.lesson_1_task2_coding_bat.payload.ProblemDto;
import com.example.lesson_1_task2_coding_bat.repository.ProblemRepository;
import com.example.lesson_1_task2_coding_bat.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemRepository problemRepository;
    private final TopicRepository topicRepository;

    public ApiResponse add(ProblemDto problemDto) {
        Long topicId = problemDto.getTopicId();
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isEmpty()) return new ApiResponse("Topic not found", false);
        Topic topic = optionalTopic.get();
        Problem problem = new Problem();
        problem.setTopic(topic);
        problem.setOrdinal(problemDto.getOrdinal());
        problem.setText(problemDto.getText());
        problem.setDefault_code(problemDto.getDefault_code());
        problemRepository.save(problem);
        return new ApiResponse("Added successfully", true);
    }

    public ApiResponse get() {
        List<Problem> all = problemRepository.findAll();
        return new ApiResponse("Barcha problemlar ro'yhati", true, all);
    }

    public ApiResponse getById(Long id) {
        Optional<Problem> optionalProblem = problemRepository.findById(id);
        return optionalProblem.map(problem
                        -> new ApiResponse("Problem by id : " + id, true, problem))
                .orElseGet(()
                        -> new ApiResponse("Problem not found", false));
    }

    public ApiResponse deleteById(Long id) {
        try {
            problemRepository.deleteById(id);
            return new ApiResponse("Deleted success", true);
        } catch (Exception e) {
            return new ApiResponse("id not found", false);
        }
    }

    public ApiResponse editById(Long id, ProblemDto problemDto) {
        Optional<Problem> optionalProblem = problemRepository.findById(id);
        if (optionalProblem.isEmpty()) return new ApiResponse("id not valid", false);
        Problem problem = optionalProblem.get();
        Long topicId = problemDto.getTopicId();
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        optionalTopic.ifPresent(problem::setTopic);
        Integer ordinal = problemDto.getOrdinal();
        String text = problemDto.getText();
        String default_code = problemDto.getDefault_code();
        if (ordinal != null) problem.setOrdinal(ordinal);
        if (text != null) problem.setText(text);
        if (default_code != null) problem.setDefault_code(default_code);
        problemRepository.save(problem);
        return new ApiResponse("edited successfully", true);
    }
}
