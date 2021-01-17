package pe.gob.munisullana.sistrado.commands;

import pe.gob.munisullana.sistrado.controllers.webapp.dto.LoginRequest;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.LoginResponse;

public interface LoginAccountCommand {

    LoginResponse execute(LoginRequest loginRequest);
}
