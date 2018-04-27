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
    protected int index;

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
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Node node = (Node) o;

        if (index != node.index)
            return false;
        if (uuid != null ? !uuid.equals(node.uuid) : node.uuid != null)
            return false;
        if (simpleName != null ? !simpleName.equals(node.simpleName) : node.simpleName != null)
            return false;
        if (packageName != null ? !packageName.equals(node.packageName) : node.packageName != null)
            return false;
        if (groupId != null ? !groupId.equals(node.groupId) : node.groupId != null)
            return false;
        if (artifactId != null ? !artifactId.equals(node.artifactId) : node.artifactId != null)
            return false;
        return version != null ? version.equals(node.version) : node.version == null;
    }

    @Override public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (simpleName != null ? simpleName.hashCode() : 0);
        result = 31 * result + (packageName != null ? packageName.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (artifactId != null ? artifactId.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + index;
        return result;
    }
}
