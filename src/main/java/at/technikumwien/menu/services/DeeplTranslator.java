package at.technikumwien.menu.services;

import at.technikumwien.menu.interfaces.CustomTranslator;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DeeplTranslator implements CustomTranslator {

    @Value("${authKey}")
    private String authKey;

    private static DeeplTranslator instance;
    private Translator translator;

    private DeeplTranslator() {
    }

    public static DeeplTranslator getInstance() {
        if (instance == null) {
            instance = new DeeplTranslator();
        }
        return instance;
    }

    public String translate(String text, String sourceLang, String targetLang) throws Exception {
        if (translator == null)
            translator = new Translator(authKey);
        TextResult result =
                translator.translateText(text, sourceLang, targetLang);
        return result.getText();
    }
}
