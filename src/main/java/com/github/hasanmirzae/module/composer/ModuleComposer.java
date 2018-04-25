package com.github.hasanmirzae.module.composer;

import com.github.hasanmirzae.module.composer.model.Descriptor;
import com.github.hasanmirzae.module.composer.model.ModuleDescription;
import com.github.hasanmirzae.module.composer.utils.FileUtils;
import org.apache.commons.text.StringSubstitutor;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ModuleComposer {
    private Descriptor descriptor;
    public ModuleComposer(Descriptor descriptor){
        this.descriptor = descriptor;
    }

    public String compose() throws IOException {
        Map<String,String> map = new HashMap<String, String>();
        map.put("moduleName", descriptor.getArtifactId());
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

    private ModuleDescription getEntryPoint(){
        return descriptor.getEntryPoint();
    }

    private ModuleDescription getEndPoint(){
        return  descriptor.getEndPoint();
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
       return FileUtils.readFile(file);
    }

    public void generateProject(String projectDist) throws IOException {
        String root = Paths.get(projectDist,descriptor.getArtifactId()).toString();
        FileUtils.createDirectory(root);
        String srcDir = Paths.get(root,"src/main/java/",Arrays.stream(descriptor.getPackageName().split("\\.")).collect(Collectors.joining("/"))).toString();
        FileUtils.createDirectory(srcDir);
        FileUtils.writeTextFile(srcDir+"/"+descriptor.getSimpleName()+".java",compose());
        FileUtils.writeTextFile(Paths.get(root,"pom.xml").toString(),composePom());

    }

    private String composePom() throws IOException {
        Map<String,String> map = new HashMap<>();
        map.put("groupId", descriptor.getPackageName());
        map.put("artifactId", descriptor.getArtifactId());
        map.put("dependencies", generateDependencies());
        StringSubstitutor sub = new StringSubstitutor(map);
        return sub.replace(getPomTemplate());

    }
    private String getPomTemplate() throws IOException {
        String file = System.getProperty("user.dir")+"/src/main/resources/PomTemplate.xml";
        return FileUtils.readFile(file);
    }

    private String generateDependencies() {

        return descriptor.getConnections()
                .stream()
                .flatMap(con -> Arrays.asList(con.getFrom(),con.getTo()).stream())
                .map(m ->generateDependeny(m))
                .collect(Collectors.joining("\n"));

    }

    private String generateDependeny(ModuleDescription m) {
        Map<String,String> map = new HashMap<>();
        map.put("groupId", m.getGroupId());
        map.put("artifactId", m.getArtifactId());
        map.put("version", m.getVersion());
        StringSubstitutor sub = new StringSubstitutor(map);
        return sub.replace(
                "<dependency>\n" +
                "   <groupId>${groupId}</groupId>\n" +
                "   <artifactId>${artifactId}</artifactId>\n" +
                "   <version>${version}</version>\n" +
                "</dependency>\n");
    }

}
