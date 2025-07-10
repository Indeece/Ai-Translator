package ru.indeece.aitranslator.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

public interface AiService {
    public String translate(String message, String fromLang, String toLang);
}
