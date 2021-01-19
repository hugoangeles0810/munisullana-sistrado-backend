package pe.gob.munisullana.sistrado.commands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.gob.munisullana.sistrado.commands.GetAllTramitesCommand;
import pe.gob.munisullana.sistrado.entities.Tramite;
import pe.gob.munisullana.sistrado.repositories.TramiteRepository;

import java.util.List;

@Service
public class GetAllTramitesCommandImpl implements GetAllTramitesCommand {

    private TramiteRepository tramiteRepository;

    @Autowired
    public GetAllTramitesCommandImpl(TramiteRepository tramiteRepository) {
        this.tramiteRepository = tramiteRepository;
    }

    @Override
    public List<Tramite> execute() {
        return tramiteRepository.findAll(Sort.by("id"));
    }

}
