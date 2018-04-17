package com.github.hasanmirzae.module.composer;

import com.github.hasanmirzae.module.Configuration;

public class ModuleDescription {
    private String simpleName;
    private String packageName;
    private String inputType;
    private String outputType;
    private boolean entryPoint;
    private boolean endPoint;
    private String config;

    public ModuleDescription(String simpleName,String packageName, String inputType, String outputType,
            boolean entryPoint, boolean endPoint) {
        this.simpleName = simpleName;
        this.packageName = packageName;
        this.inputType = inputType;
        this.outputType = outputType;
        this.entryPoint = entryPoint;
        this.endPoint = endPoint;
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

    public String getInstanceName() {
        if (isEntryPoint())
            return  "entryPoint";
        if (isEndPoint())
            return  "endPoint";
        return getSimpleName().toLowerCase();
    }
}
