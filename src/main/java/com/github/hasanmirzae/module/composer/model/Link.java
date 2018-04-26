package com.github.hasanmirzae.module.composer.model;

import java.util.Objects;

public class Link {
    private String source;
    private String target;

    public Link() {
    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(source, link.source) &&
                Objects.equals(target, link.target);
    }

    @Override
    public int hashCode() {

        return Objects.hash(source, target);
    }
}
