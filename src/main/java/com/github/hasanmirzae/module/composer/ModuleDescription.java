package com.github.hasanmirzae.module.composer;

public class ModuleDescription {
    private String simpleName;
    private String inputType;
    private String outputType;
    private boolean entryPoint;
    private boolean endPoint;

    public ModuleDescription(String simpleName, String inputType, String outputType,
            boolean entryPoint, boolean endPoint) {
        this.simpleName = simpleName;
        this.inputType = inputType;
        this.outputType = outputType;
        this.entryPoint = entryPoint;
        this.endPoint = endPoint;
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
