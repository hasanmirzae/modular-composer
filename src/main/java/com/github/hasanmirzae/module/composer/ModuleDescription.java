package com.github.hasanmirzae.module.composer;

import com.github.hasanmirzae.module.Configuration;

public class ModuleDescription {
    private String simpleName;
    private String inputType;
    private String outputType;
    private boolean entryPoint;
    private boolean endPoint;
    private Configuration config;

    public ModuleDescription(String simpleName, String inputType, String outputType,
            boolean entryPoint, boolean endPoint, Configuration config) {
        this.simpleName = simpleName;
        this.inputType = inputType;
        this.outputType = outputType;
        this.entryPoint = entryPoint;
        this.endPoint = endPoint;
        this.config = config;
    }

    public String getSimpleName() {
        return simpleName;
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
}
