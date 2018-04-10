package com.github.hasanmirzae.module.composer;

import java.util.ArrayList;
import java.util.List;

public class Descriptor {

    private List<Connection> connections = new ArrayList<>();
    private String moduleName;
    private String packageName;
    private String inputType;
    private String outputType;

    public Descriptor(String moduleName, String packageName, String inputType, String outputType) {
        this.moduleName = moduleName;
        this.packageName = packageName;
        this.inputType = inputType;
        this.outputType = outputType;
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
        return inputType;
    }

    public String getOutputType() {
        return outputType;
    }
}
