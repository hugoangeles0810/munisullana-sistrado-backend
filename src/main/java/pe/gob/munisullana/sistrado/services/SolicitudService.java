package pe.gob.munisullana.sistrado.services;

import pe.gob.munisullana.sistrado.controllers.webapp.dto.CrearSolicitudRequest;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.CrearSolicitudResponse;

public interface SolicitudService {

    CrearSolicitudResponse save(CrearSolicitudRequest request);
}
