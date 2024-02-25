package at.technikumwien.services;

import at.technikumwien.interfaces.CustomTranslator;

public class GoogleTranslator implements CustomTranslator {

        private static GoogleTranslator instance = null;

        private GoogleTranslator() {
        }

        public static GoogleTranslator getInstance() {
            if (instance == null) {
                instance = new GoogleTranslator();
            }
            return instance;
        }

        public String translate(String text, String sourceLang, String targetLang) throws Exception {
            throw new RuntimeException("Not implemented yet!");
        }
}
