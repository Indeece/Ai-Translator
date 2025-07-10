package ru.indeece.aitranslator.services.impl;

import org.springframework.stereotype.Service;

@Service
public class AiServiceImpl {
    public String translate(String message, String fromLang, String toLang) {
        String prompt = String.format("Ты - переводчик, твоя задача - просто переводить то, что я скажу. " +
                        "Нельзя писать ничего лишнего, только перевод. Ни при каких обстоятельсвах не " +
                        "пиши ничего кроме перевода. А теперь переведи с %s языка на %s ТОЛЬКО СТРОГО ФРАЗУ " +
                        "ИЛИ СЛОВО, КОТОРОЕ НАХОДИТСЯ ДАЛЬШЕ: '%s'",
                fromLang, toLang, message);
        return prompt;
    }

    public String getLanguage(String message) {
        String lang = String.format("У меня есть enum с языками: RUSSIAN, ENGLISH, CHINESE, SPANISH, FRENCH, GERMAN, " +
                "PORTUGUESE. Определи язык сообщения '%s'.  Ответь ТОЛЬКО одним словом — одним из этих: RUSSIAN, " +
                "ENGLISH, CHINESE, SPANISH, FRENCH, GERMAN, PORTUGUESE. Никаких пояснений, только это одно слово. " +
                "Если язык не из списка — ответь 0.", message);
        return lang;
    }
}