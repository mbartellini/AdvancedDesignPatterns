package at.technikumwien.services;

import java.io.*;

public class FileParser {

    public static String readFromFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File does not exist at path: " + filePath);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                if (reader.ready()) { // Check if there is another line
                    content.append("\n"); // Append newline only if not at the end of the file
                }
            }
        }
        return content.toString();
    }

}

