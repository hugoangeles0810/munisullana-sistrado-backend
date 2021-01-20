package pe.gob.munisullana.sistrado.commands;

import pe.gob.munisullana.sistrado.entities.Tramite;

import java.util.List;

public interface GetAllTramitesCommand {

    List<Tramite> execute();
}
