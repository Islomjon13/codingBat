package com.example.lesson_1_task2_coding_bat.service;

import com.example.lesson_1_task2_coding_bat.entity.SampleAnswers;
import com.example.lesson_1_task2_coding_bat.entity.Problem;
import com.example.lesson_1_task2_coding_bat.payload.ApiResponse;
import com.example.lesson_1_task2_coding_bat.payload.SampleAnswersDto;
import com.example.lesson_1_task2_coding_bat.repository.ProblemRepository;
import com.example.lesson_1_task2_coding_bat.repository.SampleAnswersRepository;
import com.example.lesson_1_task2_coding_bat.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SampleAnswersService {
    private final SampleAnswersRepository sampleAnswersRepository;
    private final ProblemRepository problemRepository;

    public ApiResponse add(SampleAnswersDto sampleAnswersDto) {
        Long problemId = sampleAnswersDto.getProblemId();
        Optional<Problem> optionalProblem = problemRepository.findById(problemId);
        if (optionalProblem.isEmpty()) return new ApiResponse("Problem not found", false);
        Problem problem = optionalProblem.get();
        SampleAnswers sampleAnswers = new SampleAnswers();
        sampleAnswers.setProblem(problem);

        sampleAnswers.setSample(sampleAnswersDto.getSample());
        sampleAnswers.setAnswer(sampleAnswersDto.getAnswer());
        sampleAnswersRepository.save(sampleAnswers);
        return new ApiResponse("Added successfully", true);
    }

    public ApiResponse get() {
        List<SampleAnswers> all = sampleAnswersRepository.findAll();
        return new ApiResponse("Barcha sampleAnswerslar ro'yhati", true, all);
    }

    public ApiResponse getById(Long id) {
        Optional<SampleAnswers> optionalSampleAnswers = sampleAnswersRepository.findById(id);
        return optionalSampleAnswers.map(sampleAnswers
                        -> new ApiResponse("SampleAnswers by id : " + id, true, sampleAnswers))
                .orElseGet(()
                        -> new ApiResponse("SampleAnswers not found", false));
    }

    public ApiResponse deleteById(Long id) {
        try {
            sampleAnswersRepository.deleteById(id);
            return new ApiResponse("Deleted success", true);
        } catch (Exception e) {
            return new ApiResponse("id not found", false);
        }
    }

    public ApiResponse editById(Long id, SampleAnswersDto sampleAnswersDto) {
        Optional<SampleAnswers> optionalSampleAnswers = sampleAnswersRepository.findById(id);
        if (optionalSampleAnswers.isEmpty()) return new ApiResponse("id not valid", false);
        SampleAnswers sampleAnswers = optionalSampleAnswers.get();
        Long problemId = sampleAnswersDto.getProblemId();
        Optional<Problem> optionalProblem = problemRepository.findById(problemId);
        optionalProblem.ifPresent(sampleAnswers::setProblem);

        String sample = sampleAnswersDto.getSample();
        String answer = sampleAnswersDto.getAnswer();

        if (sample != null) sampleAnswers.setSample(sample);
        if (answer != null) sampleAnswers.setAnswer(answer);
        sampleAnswersRepository.save(sampleAnswers);
        return new ApiResponse("edited successfully", true);
    }
}
