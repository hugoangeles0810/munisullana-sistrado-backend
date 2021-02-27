package pe.gob.munisullana.sistrado.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.gob.munisullana.sistrado.controllers.common.dto.TramiteDetailResponse;
import pe.gob.munisullana.sistrado.entities.Requisito;
import pe.gob.munisullana.sistrado.entities.Tramite;
import pe.gob.munisullana.sistrado.repositories.RequisitoRepository;
import pe.gob.munisullana.sistrado.repositories.TramiteRepository;
import pe.gob.munisullana.sistrado.services.TramiteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TramiteServiceImpl implements TramiteService {

    private TramiteRepository tramiteRepository;
    private RequisitoRepository requisitoRepository;

    @Autowired
    public TramiteServiceImpl(TramiteRepository tramiteRepository,
                              RequisitoRepository requisitoRepository) {
        this.tramiteRepository = tramiteRepository;
        this.requisitoRepository = requisitoRepository;
    }

    @Override
    public List<Tramite> getAll() {
        return tramiteRepository.findAll(Sort.by("id"));
    }

    @Override
    public TramiteDetailResponse getTramiteDetail(Integer id) {
        Optional<Tramite> holder = tramiteRepository.findById(id);

        if (!holder.isPresent()) return null;

        Tramite tramite = holder.get();

        List<Requisito> requisitos = requisitoRepository.findByTramiteId(id);

        return new TramiteDetailResponse(
               tramite.getId(),
               tramite.getCodigo(),
               tramite.getNombre(),
               tramite.getDescripcion(),
               tramite.getIndicaciones(),
               requisitos.stream().map(requisito -> new TramiteDetailResponse.RequisitoItemResponse(
                       requisito.getId(),
                       requisito.getNombre(),
                       requisito.getDescripcion(),
                       requisito.getIndicaciones(),
                       requisito.getTipoAdjunto()
               )).collect(Collectors.toList())
        );
    }

}
