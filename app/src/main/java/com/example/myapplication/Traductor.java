package com.example.myapplication;

import net.suuft.libretranslate.Language;

public class Traductor {

    SpecificTranslator translator = new SpecificTranslator();

    public Traductor() {}

    public String traducir(String text) {
        return translator.translate(Language.SPANISH, Language.ENGLISH, text);
    }
}
