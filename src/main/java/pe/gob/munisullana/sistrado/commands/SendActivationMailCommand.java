package pe.gob.munisullana.sistrado.commands;

import pe.gob.munisullana.sistrado.entities.Ciudadano;

public interface SendActivationMailCommand {

    void execute(Ciudadano ciudadano);
}
