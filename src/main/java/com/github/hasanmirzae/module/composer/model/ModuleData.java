package com.github.hasanmirzae.module.composer.model;

import java.util.ArrayList;
import java.util.List;

public class ModuleData extends ModuleDescription{
    private List<ModuleDescription> nodes;


    public ModuleData(){
        super();
    }


    public ModuleData(ModuleDescription descr, List<ModuleDescription> nodes){
        super(descr);
        this.nodes = new ArrayList<>(nodes);
    }

    public ModuleData(List<ModuleDescription> nodes, String uuid, String simpleName, String groupId, String artifactId, String packageName, String version, ModelType inputType, ModelType outputType) {
        super(uuid,simpleName,packageName,groupId,artifactId,version,inputType,outputType);
        this.nodes = nodes;
    }

    public List<ModuleDescription> getNodes() {
        return nodes;
    }

}
