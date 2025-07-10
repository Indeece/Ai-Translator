package ru.indeece.aitranslator.controllers;

import chat.giga.springai.GigaChatModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.indeece.aitranslator.services.impl.AiServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
public class AiController {
    private final GigaChatModel gigaChatModel;
    private final AiServiceImpl aiService;

    @GetMapping("/translate")
    public String ask(@RequestParam String message,
                      @RequestParam(defaultValue = "ru") String fromLang,
                      @RequestParam(defaultValue = "eng") String toLang) {
        return gigaChatModel.call(aiService.translate(message, fromLang, toLang));
    }
}
