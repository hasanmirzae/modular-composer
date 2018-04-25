package com.github.hasanmirzae.module.composer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "module")
public class ModuleDescription {
    @Id
    private String uuid;
    private String simpleName;
    private String packageName;
    private String inputType;
    private String outputType;
    private boolean entryPoint;
    private boolean endPoint;
    private String config;
    private String groupId;
    private String artifactId;
    private String version;

    public ModuleDescription(){

    }
    public ModuleDescription(String uuid, String simpleName,String packageName, String inputType, String outputType,
            boolean entryPoint, boolean endPoint) {
        this.uuid = uuid;
        this.simpleName = simpleName;
        this.packageName = packageName;
        this.inputType = inputType;
        this.outputType = outputType;
        this.entryPoint = entryPoint;
        this.endPoint = endPoint;
    }

    public ModuleDescription(ModuleData moduleData){
        this.uuid = moduleData.getUuid();
        this.simpleName = moduleData.getSimpleName();
        this.packageName = moduleData.getPackageName();
        // TODO input/output type
        this.artifactId = moduleData.getArtifactId();
        this.groupId = moduleData.getGroupId();
        this.version = moduleData.getVersion();
    }

    public String getSimpleName() {
        return simpleName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getInputType() {
        return inputType;
    }

    public String getOutputType() {
        return outputType;
    }

    public boolean isEntryPoint() {
        return entryPoint;
    }

    public boolean isEndPoint() {
        return endPoint;
    }

    public boolean isConfigurable(){
        return config !=null;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getInstanceName() {
        if (isEntryPoint())
            return  "entryPoint";
        if (isEndPoint())
            return  "endPoint";
        return getSimpleName().toLowerCase();
    }
}
