package ru.indeece.aitranslator.enums;

public enum LangEnum {
    RUSSIAN("Русский язык"),
    ENGLISH("Английский язык"),
    CHINESE("Китайский язык"),
    SPANISH("Испанский язык"),
    FRENCH("Французский язык"),
    GERMAN("Немецкий язык"),
    PORTUGUESE("Португальский язык");

    private final String displayName;

    LangEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}