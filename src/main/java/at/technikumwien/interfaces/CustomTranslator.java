package at.technikumwien.interfaces;

public interface CustomTranslator {
    String translate(String text, String sourceLang, String targetLang) throws Exception;
}
