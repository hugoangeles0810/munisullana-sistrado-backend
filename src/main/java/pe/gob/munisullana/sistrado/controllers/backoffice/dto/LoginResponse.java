package pe.gob.munisullana.sistrado.controllers.backoffice.dto;

public class LoginResponse {

    private final String email;
    private final String nombres;
    private final String apePaterno;
    private final String apeMaterno;
    private final String oficina;
    private final String token;

    public LoginResponse(String email, String nombres, String apePaterno, String apeMaterno, String oficina, String token) {
        this.email = email;
        this.nombres = nombres;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.oficina = oficina;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public String getOficina() {
        return oficina;
    }

    public String getToken() {
        return token;
    }
}
