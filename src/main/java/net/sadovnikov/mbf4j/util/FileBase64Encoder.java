package net.sadovnikov.mbf4j.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class FileBase64Encoder {

    public String getBase64(byte[] bytes) {
        String base64 = new String(Base64.getEncoder().encode(bytes));

        return base64;
    }

    public String getBase64(File file) throws FileNotFoundException, IOException {
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        inputStream.read(bytes);

        return getBase64(bytes);
    }
}
