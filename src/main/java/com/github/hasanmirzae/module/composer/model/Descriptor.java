package com.github.hasanmirzae.module.composer.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Descriptor extends ModuleDescription{

    private List<Connection> connections = new ArrayList<>();
    private List<ModuleDescription> modules = new ArrayList<>();
    private ModuleDescription entryModule;
    private ModuleDescription outputModule;


    public Descriptor(String uuid,String simpleName,String packageName,String groupId,String artifactId,String version,ModelType inputType,ModelType outputType){
        super(uuid,simpleName,packageName,groupId,artifactId,version,inputType,outputType);
    }

    public Descriptor(ModuleDescription descr){
        super(descr.uuid,descr.simpleName,descr.packageName,descr.groupId,descr.artifactId,descr.version,descr.inputType,descr.outputType);
    }

    public ModuleDescription getEntryModule() {
        return entryModule;
    }

    public void setEntryModule(ModuleDescription entryModule) {
        this.entryModule = entryModule;
    }

    public ModuleDescription getOutputModule() {
        return outputModule;
    }

    public void setOutputModule(ModuleDescription outputModule) {
        this.outputModule = outputModule;
    }

    public void addConnection(Connection conn){
        connections.add(conn);
    }
    public void addConnections(Collection<Connection> connections){
        connections.addAll(connections);
    }

    public void removeConnection(Connection conn){
        connections.remove(conn);
    }

    public List<Connection> getConnections() {
        return connections;
    }



    public ModelType getInputType() {
        return getOutputModule().getInputType();
    }

    public List<ModuleDescription> getModules() {
        return modules;
    }

    public void addModule(ModuleDescription module){
        this.modules.add(module);
    }

    public void removeModule(ModuleDescription module){
        this.modules.remove(module);
    }

}
