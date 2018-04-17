package com.github.hasanmirzae.module.composer;

import java.util.ArrayList;
import java.util.List;

public class Descriptor {

    private List<Connection> connections = new ArrayList<>();
    private String moduleName;
    private String packageName;

    public Descriptor(String moduleName, String packageName) {
        this.moduleName = moduleName;
        this.packageName = packageName;
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

    public String getModuleName() {
        return moduleName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getInputType() {
        return getEndPoint().getInputType();
    }

    public ModuleDescription getEntryPoint(){
        return getConnections().stream()
                        .filter(con -> con.getFrom().isEntryPoint())
                        .findFirst().get().getFrom();
    }

    public ModuleDescription getEndPoint(){
        return getConnections().stream()
                               .filter(con -> con.getTo().isEndPoint())
                               .findFirst().get().getTo();
    }

    public String getOutputType() {
        return getEndPoint().getOutputType();
    }
}
