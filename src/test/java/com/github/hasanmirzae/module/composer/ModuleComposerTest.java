package com.github.hasanmirzae.module.composer;

import com.github.hasanmirzae.module.Configuration;
import com.github.hasanmirzae.module.composer.utils.FileUtils;
import org.junit.Test;

import java.io.IOException;

public class ModuleComposerTest {

    @Test
    public void test() throws IOException {
        Descriptor descriptor = new Descriptor("SampleModule","com.github.hasanmirzae.module.composer");
        Configuration config = new Configuration();
        ModuleDescription entry = new ModuleDescription("SampleModule","org.edu.modules","String","String",true,false);
        entry.setConfig("key1:value1,key2;value2");
        ModuleDescription end = new ModuleDescription("SampleModule","org.edu.modules","String","String",false,true);
        Connection conn = new Connection(entry,end);
        descriptor.addConnection(conn);
        ModuleComposer composer = new ModuleComposer(descriptor);
        FileUtils.writeTextFile("SampleModule.java",composer.compose());
    }
}
