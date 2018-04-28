package com.github.hasanmirzae.module.composer.model;

public class ActionResponse {
    private final String message;

    public ActionResponse(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
