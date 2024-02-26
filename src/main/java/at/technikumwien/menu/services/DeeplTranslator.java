package at.technikumwien.menu.services;

import at.technikumwien.menu.UnableToTranslateException;
import at.technikumwien.menu.interfaces.CustomTranslator;
import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DeeplTranslator implements CustomTranslator {

    @Value("${authKey}")
    private String authKey;
    private Translator translator;

    private DeeplTranslator() {
    }

    public String translate(String text, String sourceLang, String targetLang) {
        if (translator == null)
            translator = new Translator(authKey);
        TextResult result;
        try {
            result = translator.translateText(text, sourceLang, targetLang);
        } catch (Exception e) {
            throw new UnableToTranslateException();
        }
        return result.getText();
    }
}
