package at.technikumwien.menu.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface CustomTranslator {
    String translate(String text, String sourceLang, String targetLang);
}
