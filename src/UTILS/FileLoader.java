package UTILS;

import java.io.*;

public class FileLoader {

    public static String getFileContent(String filename) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null)
                fileContent.append(line);

            return fileContent.toString();
        }
        catch (FileNotFoundException e) {
            return "";
        }

    }
}
