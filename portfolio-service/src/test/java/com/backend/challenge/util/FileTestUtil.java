package com.backend.challenge.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

public class FileTestUtil {
	
	private FileTestUtil() {
        throw new UnsupportedOperationException();
    }

    public static String readFile(String pathFile) throws IOException {
        File file = new File("src/test/resources" + pathFile);
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }
}
