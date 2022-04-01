package com.example.lesson_1_task2_coding_bat.controller;

import com.example.lesson_1_task2_coding_bat.payload.ApiResponse;
import com.example.lesson_1_task2_coding_bat.payload.TopicDto;
import com.example.lesson_1_task2_coding_bat.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topic")
public class TopicController {
    private final TopicService topicService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody TopicDto topicDto) {
        ApiResponse apiResponse = topicService.add(topicDto);
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        ApiResponse apiResponse = topicService.get();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ApiResponse apiResponse = topicService.getById(id);
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editById(@PathVariable Long id, @RequestBody TopicDto topicDto) {
        ApiResponse apiResponse = topicService.editById(id, topicDto);
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        ApiResponse apiResponse = topicService.deleteById(id);
        return ResponseEntity
                .status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }
}
