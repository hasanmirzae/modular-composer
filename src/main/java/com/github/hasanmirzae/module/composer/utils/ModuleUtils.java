package com.github.hasanmirzae.module.composer.utils;

import com.github.hasanmirzae.module.composer.exception.ModuleNotFountException;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ModuleUtils {

    public static void throwNotFoundModuleException(String uuid){
        throw  new ModuleNotFountException(String.format("Module not found with uuid = %s",uuid));
    }

    public static String execute(String workingDirectory, String[] commands) throws IOException {
        ProcessBuilder pb =new ProcessBuilder(commands);
//        Map<String, String> env = pb.environment();
        pb.directory(new File(workingDirectory));
//        File log = new File("module-build.log");
//        pb.redirectErrorStream(true);
//        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(log));
//        Process p = pb.start();
//        assert pb.redirectInput() == ProcessBuilder.Redirect.PIPE;
//        assert pb.redirectOutput().file() == log;
//        assert p.getInputStream().read() == -1;
        String output = IOUtils.toString(new InputStreamReader(pb.start().getInputStream()));;
        System.out.println(output);
        return output;
    }
}
