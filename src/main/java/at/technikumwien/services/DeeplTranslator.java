package at.technikumwien.services;
import com.deepl.api.*;
import at.technikumwien.interfaces.CustomTranslator;

public class DeeplTranslator implements CustomTranslator {

    private static DeeplTranslator instance = null;
    private Translator translator = null;

    private DeeplTranslator() {
        String authKey = null;
        try {
            authKey = FileParser.readFromFile(System.getProperty("user.home") + "/.deepl/auth-key.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (authKey == null) {
            throw new RuntimeException("Could not read auth key");
        }
        translator = new Translator(authKey);
    }

    public static DeeplTranslator getInstance() {
        if (instance == null) {
            instance = new DeeplTranslator();
        }
        return instance;
    }

    public String translate(String text, String sourceLang, String targetLang) throws Exception {
        TextResult result =
                translator.translateText(text, sourceLang, targetLang);
        return result.getText();
    }
}
