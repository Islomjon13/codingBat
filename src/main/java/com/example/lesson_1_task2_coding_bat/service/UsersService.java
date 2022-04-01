package com.example.lesson_1_task2_coding_bat.service;

import com.example.lesson_1_task2_coding_bat.entity.Users;
import com.example.lesson_1_task2_coding_bat.payload.ApiResponse;
import com.example.lesson_1_task2_coding_bat.payload.UsersDto;
import com.example.lesson_1_task2_coding_bat.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public ApiResponse add(UsersDto usersDto) {
        String email = usersDto.getEmail();
        boolean existsByEmail = usersRepository.existsByEmail(email);
        if (existsByEmail)
            return new ApiResponse("Bunday user mail mavjud", false);
        else {
            Users users = new Users();
            users.setEmail(email);
            users.setPassword(usersDto.getPassword());
            usersRepository.save(users);
            return new ApiResponse("Saved successfully", true);
        }
    }

    public ApiResponse get() {
        List<Users> all = usersRepository.findAll();
        return new ApiResponse("Barcha userlar ro'yhati", true, all);
    }

    public ApiResponse getById(Long id) {
        Optional<Users> optionalUsers = usersRepository.findById(id);
        return optionalUsers.map(users
                        -> new ApiResponse("Users by id" + id, true, users))
                .orElseGet(()
                        -> new ApiResponse("Users not found", false));
    }

    public ApiResponse deleteById(Long id) {
        try {
            usersRepository.deleteById(id);
            return new ApiResponse("Deleted success", true);
        } catch (Exception e) {
            return new ApiResponse("Deleted unsuccessful", false);
        }
    }

    public ApiResponse editById(Long id, UsersDto usersDto) {
        Optional<Users> optionalUsers = usersRepository.findById(id);
        if (optionalUsers.isEmpty()) return new ApiResponse("Not found", false);
        Users users = optionalUsers.get();

        String email = usersDto.getEmail();
        String password = usersDto.getPassword();
        if (email != null) users.setEmail(email);
        if(password != null) users.setPassword(password);
        usersRepository.save(users);
        return new ApiResponse("Edited successfully", true);
    }
}
