package com.github.hasanmirzae.module.composer.model;

public class Link {
    private String source;
    private String target;

    public Link(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
