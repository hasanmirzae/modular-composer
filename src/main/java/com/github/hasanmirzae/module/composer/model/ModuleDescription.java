package com.github.hasanmirzae.module.composer.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document(collection = "module")
public class ModuleDescription extends Node{

    protected String inputType;
    protected String outputType;
    protected String config;
    private List<Link> links = new ArrayList<>();

    public ModuleDescription(String uuid, String simpleName,String packageName,String groupId, String artifactId, String version, String inputType, String outputType) {
        super(simpleName,packageName,groupId,artifactId,version,uuid);
        this.inputType = inputType;
        this.outputType = outputType;
    }

    public ModuleDescription(ModuleData moduleData){
        super(moduleData.simpleName,moduleData.packageName,moduleData.groupId,moduleData.artifactId,moduleData.version,moduleData.uuid);
        // TODO input/output type
    }


    public String getInputType() {
        return inputType;
    }

    public String getOutputType() {
        return outputType;
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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getInstanceName() {
        return getSimpleName().toLowerCase();
    }
}
