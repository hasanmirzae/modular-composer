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
import java.util.Set;
import java.util.stream.Collectors;

public class ModuleComposer {
    private Descriptor descriptor;
    private Map<String, String> fieldNamesMap = new HashMap<>();

    public ModuleComposer(Descriptor descriptor){
        this.descriptor = descriptor;
    }

    public String compose() throws IOException {
        Map<String,String> map = new HashMap<String, String>();
        map.put("moduleName", descriptor.getSimpleName());
        map.put("initDependencies", generateFields());
        map.put("inputType", descriptor.getInputType().getSimpleName());
        map.put("outputType", descriptor.getOutputType().getSimpleName());
        map.put("packageName",descriptor.getPackageName());
        map.put("internalConnections", generateInternalConnections());
        map.put("importTypes", generateTypesImport());
        StringSubstitutor sub = new StringSubstitutor(map);
        initFieldNames();
        return sub.replace(getTemplate());
    }

    private void initFieldNames() {
        descriptor.getModules().stream().forEach(m -> fieldNamesMap.put(m.getUuid(),
                m.getInstanceName() + fieldNamesMap.values().stream().filter(name -> name
                        .startsWith(m.getInstanceName())).count()));

    }

    private String generateTypesImport() {
        Set<String> types = descriptor.getModules().stream()
                  .flatMap(m -> Arrays.asList(m.getInputType().getId(),m.getOutputType().getId()).stream())
                  .collect(Collectors.toSet());
        types.add(descriptor.getInputType().getId());
        types.add(descriptor.getOutputType().getId());
        return types.stream().map(type -> "import " + type + ';').collect(Collectors.joining("\n"));
    }

    private String generateInternalConnections() {
        return descriptor.getConnections().stream()
                  .map(conn ->String.format("%s.addConsumer(%s)",fieldNamesMap.get(conn.getSource().getInstanceName()),fieldNamesMap.get(conn.getTarget().getInstanceName()))+";")
                  .collect(Collectors.joining("\n"));
    }

    private String generateFields() {
        return descriptor.getConnections()
                  .stream()
                  .flatMap(con -> Arrays.asList(con.getSource(),con.getTarget()).stream())
                  .map(m ->generatePrivateField(m, generateFieldName(m)))
                  .collect(Collectors.joining("\n"));
    }

    private String generateFieldName(ModuleDescription m) {
        if (m.getUuid().equals(descriptor.getEntryModule().getUuid()))
            return "entryPoint";
        if (m.getUuid().equals(descriptor.getOutputModule().getUuid()))
            return "endPoint";
        return getFieldNameByUuid(m.getUuid());
    }

    private String getFieldNameByUuid(String uuid) {
        return this.fieldNamesMap.get(uuid);
    }

    private String generatePrivateField(ModuleDescription module,String fieldName){
        if (module.isConfigurable())
            return String.format("private final Module<%s,%s> %s = new %s<>(new Configuration(\"%s\"));",module.getInputType().getSimpleName(),module.getOutputType().getSimpleName(),fieldName,module.getSimpleName(),module.getConfig());
        return String.format("private final Module<%s,%s> %s = new %s<>();",module.getInputType().getSimpleName(),module.getOutputType().getSimpleName(),fieldName,module.getSimpleName());
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
                .flatMap(con -> Arrays.asList(con.getSource(),con.getTarget()).stream())
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
