package com.github.hasanmirzae.module.composer.model;

public class Connection {

    private ModuleDescription source;
    private ModuleDescription target;

    public Connection(ModuleDescription source, ModuleDescription target) {
        this.source = source;
        this.target = target;
    }

    public ModuleDescription getSource() {
        return source;
    }

    public ModuleDescription getTarget() {
        return target;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Connection that = (Connection) o;

        if (source != null ? !source.equals(that.source) : that.source != null)
            return false;
        return target != null ? target.equals(that.target) : that.target == null;
    }

    @Override public int hashCode() {
        int result = source != null ? source.hashCode() : 0;
        result = 31 * result + (target != null ? target.hashCode() : 0);
        return result;
    }
}
