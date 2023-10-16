package com.example.myapplication;

import net.suuft.libretranslate.Language;

public class Traductor {


    SpecificTranslator translator = new SpecificTranslator();

    public Traductor() {}

    public String traducir(String idioma_origin,String idioma_destination,String text) {
        if (idioma_origin.equals("AUTO")){
            idioma_origin = "NONE";
        }
        text = text.toLowerCase(); //para poder traducir si se escriben mayusculas
        return translator.translate(Language.valueOf(idioma_origin), Language.valueOf(idioma_destination), text);
    }
}
