package pe.gob.munisullana.sistrado.commands;

import pe.gob.munisullana.sistrado.controllers.webapp.dto.CreateAccountRequest;

public interface CreateAccountCommand {

    void execute(CreateAccountRequest createAccountRequest);
}
