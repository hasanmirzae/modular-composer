package com.github.hasanmirzae.module.composer.model;

import java.util.ArrayList;
import java.util.List;

public class ModuleData extends ModuleDescription{
    private List<Node> nodes;


    public ModuleData(){
        super();
    }


    public ModuleData(ModuleDescription descr, List<Node> nodes){
        super(descr);
        this.nodes = new ArrayList<>(nodes);
    }

    public ModuleData(List<Node> nodes, String uuid, String simpleName, String groupId, String artifactId, String packageName, String version, ModelType inputType, ModelType outputType) {
        super(uuid,simpleName,packageName,groupId,artifactId,version,inputType,outputType);
        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return nodes;
    }

}
