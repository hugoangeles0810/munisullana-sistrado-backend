package pe.gob.munisullana.sistrado.controllers.backoffice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @Email(message = "Debe ingresar un correo válido")
    private final String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private final String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
