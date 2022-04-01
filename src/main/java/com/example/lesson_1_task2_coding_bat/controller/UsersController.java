package com.example.lesson_1_task2_coding_bat.controller;

import com.example.lesson_1_task2_coding_bat.payload.ApiResponse;
import com.example.lesson_1_task2_coding_bat.payload.UsersDto;
import com.example.lesson_1_task2_coding_bat.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody UsersDto usersDto) {
        ApiResponse apiResponse = usersService.add(usersDto);
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        ApiResponse apiResponse = usersService.get();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ApiResponse apiResponse = usersService.getById(id);
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editById(@PathVariable Long id, @RequestBody UsersDto usersDto) {
        ApiResponse apiResponse = usersService.editById(id, usersDto);
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        ApiResponse apiResponse = usersService.deleteById(id);
        return ResponseEntity
                .status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

}
