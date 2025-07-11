package ru.indeece.aitranslator.servicesTest;

import org.junit.jupiter.api.Test;
import ru.indeece.aitranslator.services.impl.AiServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class AiServiceImplTest {

    private final AiServiceImpl aiService = new AiServiceImpl();

    @Test
    void translate_ValidInput_ReturnsPrompt() {
        String prompt = aiService.translate("Hello", "ENGLISH", "RUSSIAN");
        assertTrue(prompt.contains("переведи с ENGLISH языка на RUSSIAN"));
    }

    @Test
    void getLanguage_EnglishText_ReturnsEnglish() {
        String prompt = aiService.getLanguage("Hello");
        assertTrue(prompt.contains("Ответь ТОЛЬКО одним словом"));
    }
}