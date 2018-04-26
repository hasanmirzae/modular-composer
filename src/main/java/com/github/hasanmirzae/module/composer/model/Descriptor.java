package com.github.hasanmirzae.module.composer.model;

import java.util.ArrayList;
import java.util.List;

public class Descriptor extends ModuleDescription{

    private List<Connection> connections = new ArrayList<>();
    private String entryModuleUuid;
    private String outputModuleUuid;


    public Descriptor(String uuid,String simpleName,String packageName,String groupId,String artifactId,String version,ModelType inputType,ModelType outputType){
        super(uuid,simpleName,packageName,groupId,artifactId,version,inputType,outputType);
    }

    public Descriptor(ModuleDescription descr){
        super(descr.uuid,descr.simpleName,descr.packageName,descr.groupId,descr.artifactId,descr.version,descr.inputType,descr.outputType);
    }

    public String getEntryModuleUuid() {
        return entryModuleUuid;
    }

    public void setEntryModuleUuid(String entryModuleUuid) {
        this.entryModuleUuid = entryModuleUuid;
    }

    public String getOutputModuleUuid() {
        return outputModuleUuid;
    }

    public void setOutputModuleUuid(String outputModuleUuid) {
        this.outputModuleUuid = outputModuleUuid;
    }

    public void addConnection(Connection conn){
        connections.add(conn);
    }

    public void removeConnection(Connection conn){
        connections.remove(conn);
    }

    public List<Connection> getConnections() {
        return connections;
    }


    public ModelType getInputType() {
        return getEndPoint().getInputType();
    }

    public ModuleDescription getEntryPoint(){
        return getConnections().stream()
                        .filter(con -> con.getFrom().getUuid().equals(this.entryModuleUuid))
                        .findFirst().get().getFrom();
    }

    public ModuleDescription getEndPoint(){
        return getConnections().stream()
                               .filter(con -> con.getTo().getUuid().equals(this.outputModuleUuid))
                               .findFirst().get().getTo();
    }

    public ModelType getOutputType() {
        return getEndPoint().getOutputType();
    }
}
