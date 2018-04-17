package com.github.hasanmirzae.module.composer.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static void writeTextFile(String fileName, String content) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,false))){
            writer.write(content);
        }
    }

    public static String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static boolean createDirectory(String directory){
        return new File(directory).mkdirs();
    }

}
