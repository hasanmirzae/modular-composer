package com.github.hasanmirzae.module.composer.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class FileUtils {

    public static void writeTextFile(String fileName, String content) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,false))){
            writer.write(content);
        }
    }
}
