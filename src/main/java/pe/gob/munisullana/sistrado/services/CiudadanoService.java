package pe.gob.munisullana.sistrado.services;

import pe.gob.munisullana.sistrado.controllers.webapp.dto.CreateAccountRequest;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.LoginRequest;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.LoginResponse;
import pe.gob.munisullana.sistrado.entities.Ciudadano;

public interface CiudadanoService {

    void save(CreateAccountRequest createAccountRequest);

    LoginResponse login(LoginRequest loginRequest);

    void sendActivationMail(Ciudadano ciudadano);

    void verifyAccount(String token);
}
