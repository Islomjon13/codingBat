package com.example.lesson_1_task2_coding_bat.controller;

import com.example.lesson_1_task2_coding_bat.payload.ApiResponse;
import com.example.lesson_1_task2_coding_bat.payload.LanguageDto;
import com.example.lesson_1_task2_coding_bat.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/language")
public class LanguageController {
    private final LanguageService languageService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody LanguageDto languageDto) {
        ApiResponse apiResponse = languageService.add(languageDto);
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        ApiResponse apiResponse = languageService.get();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ApiResponse apiResponse = languageService.getById(id);
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editById(@PathVariable Long id, @RequestBody LanguageDto languageDto) {
        ApiResponse apiResponse = languageService.editById(id, languageDto);
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        ApiResponse apiResponse = languageService.deleteById(id);
        return ResponseEntity
                .status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }
}
