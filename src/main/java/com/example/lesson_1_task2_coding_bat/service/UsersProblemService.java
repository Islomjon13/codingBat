package com.example.lesson_1_task2_coding_bat.service;

import com.example.lesson_1_task2_coding_bat.entity.Problem;
import com.example.lesson_1_task2_coding_bat.entity.Users;
import com.example.lesson_1_task2_coding_bat.payload.ApiResponse;
import com.example.lesson_1_task2_coding_bat.payload.UsersProblemDto;
import com.example.lesson_1_task2_coding_bat.repository.ProblemRepository;
import com.example.lesson_1_task2_coding_bat.repository.UsersProblemRepository;
import com.example.lesson_1_task2_coding_bat.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersProblemService {
    private final UsersProblemRepository usersProblemRepository;
    private final UsersRepository usersRepository;
    private final ProblemRepository problemRepository;

    public ApiResponse submit(UsersProblemDto usersProblemDto) {
        Long userId = usersProblemDto.getUserId();
        Optional<Users> optionalUsers = usersRepository.findById(userId);
        if (optionalUsers.isEmpty())return new ApiResponse("User not found",false);
        Long problemId = usersProblemDto.getProblemId();
        Optional<Problem> optionalProblem = problemRepository.findById(problemId);
        if(optionalProblem.isEmpty()) return new ApiResponse("Problem not found",false);
        return null;
    }

}
