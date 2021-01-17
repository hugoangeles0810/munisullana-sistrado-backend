package pe.gob.munisullana.sistrado.controllers;

public class DomainErrorResponse {

    private String message;

    public DomainErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
