package com.example.lesson_1_task2_coding_bat.controller;

import com.example.lesson_1_task2_coding_bat.payload.ApiResponse;
import com.example.lesson_1_task2_coding_bat.payload.SampleAnswersDto;
import com.example.lesson_1_task2_coding_bat.service.SampleAnswersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sampleAnswers")
public class SampleAnswerController {
    private final SampleAnswersService sampleAnswersService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody SampleAnswersDto sampleAnswersDto) {
        ApiResponse apiResponse = sampleAnswersService.add(sampleAnswersDto);
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        ApiResponse apiResponse = sampleAnswersService.get();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ApiResponse apiResponse = sampleAnswersService.getById(id);
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editById(@PathVariable Long id, @RequestBody SampleAnswersDto sampleAnswersDto) {
        ApiResponse apiResponse = sampleAnswersService.editById(id, sampleAnswersDto);
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        ApiResponse apiResponse = sampleAnswersService.deleteById(id);
        return ResponseEntity
                .status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }
}
