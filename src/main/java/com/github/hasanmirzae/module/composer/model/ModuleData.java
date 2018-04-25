package com.github.hasanmirzae.module.composer.model;

import java.util.List;

public class ModuleData extends Node{
    private List<Node> nodes;
    private List<Link> links;


    public ModuleData(ModuleDescription descr, List<Node> nodes){
        super(descr);
        this.nodes = nodes;
        this.links = descr.getLinks();
    }

    public ModuleData(List<Node> nodes, List<Link> links, String uuid, String simpleName, String groupId, String artifactId, String packageName, String version) {
        super(simpleName,packageName,groupId,artifactId,version,uuid);
        this.nodes = nodes;
        this.links = links;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Link> getLinks() {
        return links;
    }

}
