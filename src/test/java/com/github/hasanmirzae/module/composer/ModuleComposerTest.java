package com.github.hasanmirzae.module.composer;

import com.github.hasanmirzae.module.AbstractModule;
import com.github.hasanmirzae.module.Configuration;
import com.github.hasanmirzae.module.composer.model.Connection;
import com.github.hasanmirzae.module.composer.model.Descriptor;
import com.github.hasanmirzae.module.composer.model.ModelType;
import com.github.hasanmirzae.module.composer.model.ModuleDescription;
import com.github.hasanmirzae.module.composer.service.ModuleManager;
import org.junit.Test;

import java.io.IOException;
import java.util.function.Function;

public class ModuleComposerTest {


    class SampleModule extends AbstractModule<String,String>{

        @Override protected Function<String, String> getLogic() {
            return null;
        }
    }



//    @Test
//    public void generateProjectTest() throws IOException {
//        ModelType stringType = new ModelType("String","java.lang");
//        Descriptor descriptor = new Descriptor("4345345","SampleModule","com.github.hasanmirzae.module.composer","com.github.hasanmirzae.module.composer","sample-module","SNAPSHOT-1.0",stringType,stringType);
//        Configuration config = new Configuration();
//        ModuleDescription entry = new ModuleDescription("uuid-1","SampleModule","org.edu.modules","org.edu.modules","sample-module","SNAPSHOT-1.0",stringType,stringType);
//        entry.setConfig("key1:value1,key2;value2");
//        ModuleDescription end = new ModuleDescription("uuid-2","SampleModule","org.edu.modules","org.edu.modules","sample-module","SNAPSHOT-1.0",stringType,stringType);
//        Connection conn = new Connection(entry,end);
//        descriptor.addConnection(conn);
//        ModuleComposer composer = new ModuleComposer(descriptor);
//        descriptor.setEntryModuleUuid(entry.getUuid());
//        descriptor.setOutputModuleUuid(end.getUuid());
//        composer.generateProject("target");
//    }


}
