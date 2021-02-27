package pe.gob.munisullana.sistrado.services;

import pe.gob.munisullana.sistrado.controllers.common.dto.TramiteDetailResponse;
import pe.gob.munisullana.sistrado.entities.Tramite;

import java.util.List;

public interface TramiteService {

    List<Tramite> getAll();

    TramiteDetailResponse getTramiteDetail(Integer id);
}
