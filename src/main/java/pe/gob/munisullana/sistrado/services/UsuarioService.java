package pe.gob.munisullana.sistrado.services;

import pe.gob.munisullana.sistrado.controllers.backoffice.dto.LoginRequest;
import pe.gob.munisullana.sistrado.controllers.backoffice.dto.LoginResponse;

public interface UsuarioService {

    LoginResponse login(LoginRequest loginRequest);
}
