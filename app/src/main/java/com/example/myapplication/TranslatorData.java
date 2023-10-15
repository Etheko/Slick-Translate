package com.example.myapplication;

public class TranslatorData {
    private final String toLanguage;
    private final String fromLanguage;
    private final String originalText;
    private final String translatedText;

    public TranslatorData(String fromLanguage, String toLanguage, String originalText, String translatedText) {
        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
        this.originalText = originalText;
        this.translatedText = translatedText;
    }

    public String getToLanguage() {
        return toLanguage;
    }

    public String getFromLanguage() {
        return fromLanguage;
    }

    public String getOriginalText() {
        return originalText;
    }

    public String getTranslatedText() {
        return translatedText;
    }
}
