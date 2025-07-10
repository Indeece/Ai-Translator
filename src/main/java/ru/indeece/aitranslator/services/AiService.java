package ru.indeece.aitranslator.services;

public interface AiService {
    String translate(String message, String fromLang, String toLang);
    String getLanguage(String message);
}
