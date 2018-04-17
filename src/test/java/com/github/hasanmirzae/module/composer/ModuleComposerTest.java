package com.github.hasanmirzae.module.composer;

import com.github.hasanmirzae.module.Configuration;
import org.junit.Test;

import java.io.IOException;

public class ModuleComposerTest {



    @Test
    public void generateProjectTest() throws IOException {
        Descriptor descriptor = new Descriptor("SampleModule","com.github.hasanmirzae.module.composer");
        Configuration config = new Configuration();
        ModuleDescription entry = new ModuleDescription("SampleModule","org.edu.modules","String","String",true,false);
        entry.setGroupId("org.edu.modules");
        entry.setArtifactId("sample-module");
        entry.setVersion("1.0-SNAPSHOT");
        entry.setConfig("key1:value1,key2;value2");
        ModuleDescription end = new ModuleDescription("SampleModule","org.edu.modules","String","String",false,true);
        end.setGroupId("org.edu.modules");
        end.setArtifactId("sample-module");
        end.setVersion("1.0-SNAPSHOT");
        Connection conn = new Connection(entry,end);
        descriptor.addConnection(conn);
        ModuleComposer composer = new ModuleComposer(descriptor);
        composer.generateProject("target");
    }


}
