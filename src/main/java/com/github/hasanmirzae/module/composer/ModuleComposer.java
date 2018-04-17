package com.github.hasanmirzae.module.composer;

import org.apache.commons.text.StringSubstitutor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ModuleComposer {
    private Descriptor descriptor;
    public ModuleComposer(Descriptor descriptor){
        this.descriptor = descriptor;
    }

    public String compose() throws IOException {
        Map<String,String> map = new HashMap<String, String>();
        map.put("moduleName", descriptor.getModuleName());
        map.put("initDependencies", generateFields());
        map.put("inputType", descriptor.getInputType());
        map.put("outputType", descriptor.getOutputType());
        map.put("packageName",descriptor.getPackageName());
        map.put("internalConnections", generateInternalConnections());
        StringSubstitutor sub = new StringSubstitutor(map);
        return sub.replace(getTemplate());
    }

    private String generateInternalConnections() {
        return descriptor.getConnections().stream()
                  .map(conn ->String.format("%s.addConsumer(%s)",conn.getFrom().getInstanceName(),conn.getTo().getInstanceName())+";")
                  .collect(Collectors.joining("\n"));
    }

    private ModuleDescription findEntryPoint(){
        Optional<Connection> conn = descriptor.getConnections()
                  .stream()
                  .filter( c -> c.getFrom().isEntryPoint()).findFirst();
        if (!conn.isPresent())
            throw new IllegalArgumentException("Entry point module not found in connections");
        return conn.get().getFrom();
    }

    private ModuleDescription findEndPoint(){
        Optional<Connection> conn = descriptor.getConnections()
                                              .stream()
                                              .filter( c -> c.getTo().isEndPoint()).findFirst();
        if (!conn.isPresent())
            throw new IllegalArgumentException("End point module not found in connections");
        return conn.get().getTo();
    }

    private String generateFields() {
        return descriptor.getConnections()
                  .stream()
                  .flatMap(con -> Arrays.asList(con.getFrom(),con.getTo()).stream())
                  .map(m ->generatePrivateField(m,m.getInstanceName()))
                  .collect(Collectors.joining("\n"));
    }

    private String generatePrivateField(ModuleDescription module,String fieldName){
        if (module.isConfigurable())
            return String.format("private final Module<%s,%s> %s = new %s<>(new Configuration(\"%s\"));",module.getInputType(),module.getOutputType(),fieldName,module.getSimpleName(),module.getConfig());
        return String.format("private final Module<%s,%s> %s = new %s<>();",module.getInputType(),module.getOutputType(),fieldName,module.getSimpleName());
    }

    private String getTemplate() throws IOException {
        String file = System.getProperty("user.dir")+"/src/main/resources/NewConfigurableModuleTemplate.mtl";
       return new String(Files.readAllBytes(Paths.get(file)));
    }
}
