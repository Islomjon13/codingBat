package com.example.lesson_1_task2_coding_bat.service;

import com.example.lesson_1_task2_coding_bat.entity.Language;
import com.example.lesson_1_task2_coding_bat.payload.ApiResponse;
import com.example.lesson_1_task2_coding_bat.payload.LanguageDto;
import com.example.lesson_1_task2_coding_bat.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;

    public ApiResponse add(LanguageDto languageDto) {
        String name = languageDto.getName();
        boolean existsByName = languageRepository.existsByName(name);
        if (existsByName)
            return new ApiResponse("Bunday til mavjud", false);
        else {
            Language language = new Language();
            language.setName(name);
            languageRepository.save(language);
            return new ApiResponse("Saved successfully", true);
        }
    }

    public ApiResponse get() {
        List<Language> all = languageRepository.findAll();
        return new ApiResponse("Barcha tillar ro'yhati", true, all);
    }

    public ApiResponse getById(Long id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.map(language
                        -> new ApiResponse("Language by id" + id, true, language))
                .orElseGet(()
                        -> new ApiResponse("Language not found", false));
    }

    public ApiResponse deleteById(Long id) {
        try {
            languageRepository.deleteById(id);
            return new ApiResponse("Deleted success", true);
        } catch (Exception e) {
            return new ApiResponse("Deleted unsuccessful", false);
        }
    }

    public ApiResponse editById(Long id, LanguageDto languageDto) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isEmpty()) return new ApiResponse("Not found", false);
        Language language = optionalLanguage.get();
        String name = languageDto.getName();
        if (name != null) language.setName(name);
        languageRepository.save(language);
        return new ApiResponse("Edited successfully", true);
    }
}
