package br.com.seplag.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileUtil {
    public static void writeFile(byte[] content, String filename) throws IOException {
        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fop = new FileOutputStream(file);

        fop.write(content);
        fop.flush();
        fop.close();
    }

    public static List<String> getImageExtensions() {
        List<String> extensions = Arrays.asList(".jpg", ".jpeg", ".png");
        return extensions;
    }

    public static List<String> getVideoExtensions() {
        List<String> extensions = Arrays.asList(".mp4");
        return extensions;
    }
}
