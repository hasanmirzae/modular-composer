package com.github.hasanmirzae.module.composer.model;

public class Connection {

    private ModuleDescription from;
    private ModuleDescription to;

    public Connection(ModuleDescription from, ModuleDescription to) {
        this.from = from;
        this.to = to;
    }

    public ModuleDescription getFrom() {
        return from;
    }

    public ModuleDescription getTo() {
        return to;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Connection that = (Connection) o;

        if (from != null ? !from.equals(that.from) : that.from != null)
            return false;
        return to != null ? to.equals(that.to) : that.to == null;
    }

    @Override public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
