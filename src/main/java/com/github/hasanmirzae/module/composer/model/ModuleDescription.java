package com.github.hasanmirzae.module.composer.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "module")
public class ModuleDescription extends Node{

    protected ModelType inputType;
    protected ModelType outputType;
    protected String config;
    protected Set<Link> links = new HashSet<>();

    public ModuleDescription(){
        super();
    }

    public ModuleDescription(String uuid, String simpleName,String packageName,String groupId, String artifactId, String version, ModelType inputType, ModelType outputType) {
        super(simpleName,packageName,groupId,artifactId,version,uuid);
        this.inputType = inputType;
        this.outputType = outputType;
    }

    public ModuleDescription(ModuleData moduleData){
        super(moduleData.simpleName,moduleData.packageName,moduleData.groupId,moduleData.artifactId,moduleData.version,moduleData.uuid);
        // TODO input/output type
    }

    public ModuleDescription(ModuleDescription modDescr){
        super(modDescr.simpleName, modDescr.packageName,modDescr.groupId,modDescr.artifactId,modDescr.version,modDescr.uuid);
        this.inputType = modDescr.inputType;
        this.outputType = modDescr.outputType;
        this.config = modDescr.config;
        this.links = modDescr.links;
    }


    public ModelType getInputType() {
        return inputType;
    }

    public ModelType getOutputType() {
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

    public Set<Link> getLinks() {
        return links;
    }

    public void setLinks(Set<Link> links) {
        this.links = links;
    }

    public String getInstanceName() {
        return getSimpleName().toLowerCase();
    }

}
