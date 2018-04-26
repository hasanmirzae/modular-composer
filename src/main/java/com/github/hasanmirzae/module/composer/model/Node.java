package com.github.hasanmirzae.module.composer.model;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Node {

    public Node(){

    }

    @Id
    protected String uuid;
    protected String simpleName;
    protected String packageName;
    protected String groupId;
    protected String artifactId;
    protected String version;

    public Node(String simpleName, String packageName, String groupId, String artifactId,
            String version, String uuid) {
        this.simpleName = simpleName;
        this.packageName = packageName;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.uuid = uuid;
    }

    public Node(ModuleDescription moduleDescription) {
        this.simpleName = moduleDescription.getSimpleName();
        this.packageName = moduleDescription.getPackageName();
        this.groupId = moduleDescription.getGroupId();
        this.artifactId = moduleDescription.getArtifactId();
        this.version = moduleDescription.getVersion();
        this.uuid = moduleDescription.getUuid();
    }

    public String getSimpleName() {
        return simpleName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getVersion() {
        return version;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(uuid, node.uuid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid);
    }
}
