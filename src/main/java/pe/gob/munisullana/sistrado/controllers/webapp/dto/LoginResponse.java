package pe.gob.munisullana.sistrado.controllers.webapp.dto;

public class LoginResponse {

    private String email;
    private String dni;
    private String nombres;
    private String apePaterno;
    private String apeMaterno;
    private String telefono;
    private boolean representante;
    private String ruc;
    private String razonSocial;
    private String dirFiscal;
    private String token;

    public LoginResponse(
            String email,
            String dni,
            String nombres,
            String apePaterno,
            String apeMaterno,
            String telefono,
            boolean representante,
            String ruc,
            String razonSocial,
            String dirFiscal,
            String token) {
        this.email = email;
        this.dni = dni;
        this.nombres = nombres;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.telefono = telefono;
        this.representante = representante;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.dirFiscal = dirFiscal;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getDni() {
        return dni;
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

    public String getTelefono() {
        return telefono;
    }

    public boolean isRepresentante() {
        return representante;
    }

    public String getRuc() {
        return ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getDirFiscal() {
        return dirFiscal;
    }

    public String getToken() {
        return token;
    }
}
