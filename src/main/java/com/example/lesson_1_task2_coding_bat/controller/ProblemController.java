package com.example.lesson_1_task2_coding_bat.controller;

import com.example.lesson_1_task2_coding_bat.payload.ApiResponse;
import com.example.lesson_1_task2_coding_bat.payload.ProblemDto;
import com.example.lesson_1_task2_coding_bat.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/problem")
public class ProblemController {
    private final ProblemService problemService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ProblemDto problemDto) {
        ApiResponse apiResponse = problemService.add(problemDto);
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        ApiResponse apiResponse = problemService.get();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ApiResponse apiResponse = problemService.getById(id);
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editById(@PathVariable Long id, @RequestBody ProblemDto problemDto) {
        ApiResponse apiResponse = problemService.editById(id, problemDto);
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        ApiResponse apiResponse = problemService.deleteById(id);
        return ResponseEntity
                .status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }
}
