package org.pokemon.example.api.model.response;

public class Response {
    private final int status;
    private final String message;

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}