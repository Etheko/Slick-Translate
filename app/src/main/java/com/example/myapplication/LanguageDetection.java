package com.example.myapplication;

import com.github.pemistahl.lingua.api.LanguageDetector;
import com.github.pemistahl.lingua.api.LanguageDetectorBuilder;
import com.github.pemistahl.lingua.api.Language;

public class LanguageDetection {
    public final Language[] defaultLanguages = {Language.SPANISH, Language.ENGLISH, Language.ARABIC, Language.CHINESE, Language.DANISH, Language.DUTCH, Language.FRENCH, Language.GERMAN, Language.ITALIAN, Language.JAPANESE, Language.RUSSIAN};
    private final Language[] languages;
    private LanguageDetector languageDetector;
    public LanguageDetection(Language[] languages){
        this.languages = languages;
        build();
    }
    public LanguageDetection(){
        this.languages = defaultLanguages;
        build();
    }
    private void build(){
        this.languageDetector = LanguageDetectorBuilder.fromLanguages(this.languages).build();
    }
    public String detect(String text){
        Language detectedLang = languageDetector.detectLanguageOf(text);
        return detectedLang.toString();
    }

    public Language[] getLanguages(){
        return languages;
    }
}
