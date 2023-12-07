package com.example.myapplication;

import net.suuft.libretranslate.Language;

import java.util.ArrayList;

public class Traductor {
    SpecificTranslator translator = new SpecificTranslator();

    public Traductor() {
    }

    public String traducir(String idioma_origin, String idioma_destination, String text) {
        if (idioma_origin.equals("AUTO")) {
            LanguageDetection languageDetection = new LanguageDetection();
            idioma_origin = languageDetection.detect(text);
        }
        text = text.toLowerCase(); //para poder traducir si se escriben mayusculas
        return translator.translate(Language.valueOf(idioma_origin), Language.valueOf(idioma_destination), text);
    }

    public ArrayList<TranslatorData> getHistory() {
        return translator.getHistory();
    }
}
