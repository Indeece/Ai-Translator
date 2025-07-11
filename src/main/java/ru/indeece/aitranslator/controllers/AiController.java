package ru.indeece.aitranslator.controllers;

import chat.giga.springai.GigaChatModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.indeece.aitranslator.dto.MessageToAiDto;
import ru.indeece.aitranslator.enums.LangEnum;
import ru.indeece.aitranslator.services.impl.AiServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
public class AiController {
    private final GigaChatModel gigaChatModel;
    private final AiServiceImpl aiService;

    @PostMapping("/translate")
    public String ask(@RequestBody MessageToAiDto messageToAiDto) throws Exception {
        if (messageToAiDto.getFromLang() == null) {
            messageToAiDto.setFromLang(getLang(messageToAiDto.getMessage()));
        }

        if (messageToAiDto.getToLang() == null) {
            if (messageToAiDto.getFromLang() != LangEnum.ENGLISH) {
                messageToAiDto.setToLang(LangEnum.ENGLISH);
            } else {
                messageToAiDto.setToLang(LangEnum.RUSSIAN);
            }
        }

        return gigaChatModel.call(aiService.translate(messageToAiDto.getMessage(),
                messageToAiDto.getFromLang().getDisplayName(), messageToAiDto.getToLang().getDisplayName()));
    }

    public LangEnum getLang(String message) throws Exception {
        String language = gigaChatModel.call(aiService.getLanguage(message));
        try {
            return LangEnum.valueOf(language);
        } catch (IllegalArgumentException e) {
            throw new Exception("Invalid language " + e.getMessage());
        }
    }
}
