package ru.indeece.aitranslator.dto;

import lombok.Data;
import ru.indeece.aitranslator.enums.LangEnum;

@Data
public class MessageToAiDto {
    String message;
    LangEnum fromLang;
    LangEnum toLang;
}
