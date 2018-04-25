package com.github.hasanmirzae.module.composer.model;

public class Node {
    private String simpleName;
    private String packageName;
    private String groupId;
    private String artifactId;
    private String version;
    protected String uuid;

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
}
