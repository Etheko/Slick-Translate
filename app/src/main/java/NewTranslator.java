import net.suuft.libretranslate.*;

import java.util.ArrayList;


public class NewTranslator {
    public static void main(String[] args) {
        // TEST
        String[] testTexts = {
                "Hola mundos",
                "You cannot untie a knot without knowing how it is tied.",
                "「真の知恵は、自らの無知を認識することにある」。"
        };
        SpecificTranslator myTranslator = new SpecificTranslator();
        System.out.println("1st translation: " + myTranslator.translate(Language.SPANISH, Language.ENGLISH, testTexts[0]));
        System.out.println("2nd translation: " + myTranslator.translate(Language.NONE, Language.SPANISH, testTexts[1]));
        System.out.println("3rd translation: " + myTranslator.translate(Language.JAPANESE, Language.ENGLISH, testTexts[2]));

        ArrayList<TranslatorData> history = myTranslator.getHistory();
        for (TranslatorData tData: history){
            System.out.println("----------");
            System.out.println("Text translated from " + tData.getFromLanguage() + " to " + tData.getToLanguage());
            System.out.println(">>>" + tData.getOriginalText());
            System.out.println(tData.getTranslatedText());
        }
        System.out.println("----------");
    }
}
