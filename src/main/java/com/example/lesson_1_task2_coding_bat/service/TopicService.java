package com.example.lesson_1_task2_coding_bat.service;

import com.example.lesson_1_task2_coding_bat.entity.Language;
import com.example.lesson_1_task2_coding_bat.entity.Topic;
import com.example.lesson_1_task2_coding_bat.entity.Topic;
import com.example.lesson_1_task2_coding_bat.payload.ApiResponse;
import com.example.lesson_1_task2_coding_bat.payload.TopicDto;
import com.example.lesson_1_task2_coding_bat.repository.LanguageRepository;
import com.example.lesson_1_task2_coding_bat.repository.TopicRepository;
import com.example.lesson_1_task2_coding_bat.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final LanguageRepository languageRepository;

    public ApiResponse add(TopicDto topicDto) {
        Long languageId = topicDto.getLanguageId();
        Optional<Language> optionalLanguage = languageRepository.findById(languageId);
        if (optionalLanguage.isEmpty()) return new ApiResponse("Language not found", false);
        Language language = optionalLanguage.get();
        Topic topic = new Topic();
        topic.setLanguage(language);
        topic.setOrdinal(topicDto.getOrdinal());
        topic.setName(topicDto.getName());
        topic.setDefinition(topic.getDefinition());
        topic.setStarNumbers(topicDto.getStarNumbers());
        topicRepository.save(topic);
        return new ApiResponse("Added successfully", true);
    }

    public ApiResponse get() {
        List<Topic> all = topicRepository.findAll();
        return new ApiResponse("Barcha topiclar ro'yhati", true, all);
    }

    public ApiResponse getById(Long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        return optionalTopic.map(topic
                        -> new ApiResponse("Topic by id : " + id, true, topic))
                .orElseGet(()
                        -> new ApiResponse("Topic not found", false));
    }

    public ApiResponse deleteById(Long id) {
        try {
            topicRepository.deleteById(id);
            return new ApiResponse("Deleted success", true);
        } catch (Exception e) {
            return new ApiResponse("id not found", false);
        }
    }

    public ApiResponse editById(Long id, TopicDto topicDto) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isEmpty()) return new ApiResponse("id not valid", false);
        Topic topic = optionalTopic.get();

        Long languageId = topicDto.getLanguageId();
        Optional<Language> optionalLanguage = languageRepository.findById(languageId);
        optionalLanguage.ifPresent(topic::setLanguage);

        Integer ordinal = topicDto.getOrdinal();
        String name = topicDto.getName();
        String definition = topicDto.getDefinition();
        Integer starNumbers = topicDto.getStarNumbers();

        if (ordinal != null) topic.setOrdinal(ordinal);
        if (name != null) topic.setName(name);
        if (definition != null) topic.setDefinition(definition);
        if (starNumbers != null) topic.setStarNumbers(starNumbers);
        topicRepository.save(topic);
        return new ApiResponse("edited successfully", true);
    }
}
