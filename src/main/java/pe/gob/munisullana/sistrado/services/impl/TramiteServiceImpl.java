package pe.gob.munisullana.sistrado.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.gob.munisullana.sistrado.services.TramiteService;
import pe.gob.munisullana.sistrado.entities.Tramite;
import pe.gob.munisullana.sistrado.repositories.TramiteRepository;

import java.util.List;

@Service
public class TramiteServiceImpl implements TramiteService {

    private TramiteRepository tramiteRepository;

    @Autowired
    public TramiteServiceImpl(TramiteRepository tramiteRepository) {
        this.tramiteRepository = tramiteRepository;
    }

    @Override
    public List<Tramite> getAll() {
        return tramiteRepository.findAll(Sort.by("id"));
    }

}
