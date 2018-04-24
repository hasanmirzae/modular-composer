package com.github.hasanmirzae.module.composer.model;

import java.util.List;

public class ModuleData {
    private List<ModuleDescription> nodes;
    private List<Link> links;

    public ModuleData() {
    }

    public ModuleData(List<ModuleDescription> nodes, List<Link> links) {
        this.nodes = nodes;
        this.links = links;
    }

    public List<ModuleDescription> getNodes() {
        return nodes;
    }

    public void setNodes(List<ModuleDescription> nodes) {
        this.nodes = nodes;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
