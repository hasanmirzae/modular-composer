package com.github.hasanmirzae.module.composer;

import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ModuleComposer {
    private Descriptor descriptor;
    public ModuleComposer(Descriptor descriptor){
        this.descriptor = descriptor;
    }

    public String compose(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("moduleName", descriptor.getModuleName());
        map.put("fields", generateFields());
        map.put("inputType", descriptor.getInputType());
        map.put("outputType", descriptor.getOutputType());
        map.put("return",generateReturn());
        StringSubstitutor sub = new StringSubstitutor(map);
        return sub.replace(getTemplate());
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


    private String generateReturn() {
        Map<String,String> map = new HashMap<String, String>();
        map.put("entry", findEntryPoint().getSimpleName().toLowerCase());
        map.put("end", findEndPoint().getSimpleName().toLowerCase());
        StringSubstitutor sub = new StringSubstitutor(map);
        return sub.replace("${end}.process(${entry.process(input)})");
    }

    private String generateFields() {
        Map<String,String> map = new HashMap<String, String>();


        map.put("entry", findEntryPoint().getSimpleName().toLowerCase());
        map.put("end", findEndPoint().getSimpleName().toLowerCase());
        StringSubstitutor sub = new StringSubstitutor(map);

        return null;
    }

    private String getTemplate(){

        return "class ${moduleName} extends AbstractModule<${inputType},${outputType}>{\n"
                +"// fields\n"
                +" ${fields}\n"
                + "@Override\n"
                + "protected Function<${inputType},${outputType}> getLogic() {\n"
                + "   return ${return};\n"
                + "}\n"
                + "}";
    }
}
