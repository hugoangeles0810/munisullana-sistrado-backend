package pe.gob.munisullana.sistrado.services;

import pe.gob.munisullana.sistrado.controllers.webapp.dto.CrearSolicitudRequest;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.CrearSolicitudResponse;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.ProcedureItemResponse;

import java.util.List;

public interface SolicitudService {

    CrearSolicitudResponse save(CrearSolicitudRequest request);

    List<ProcedureItemResponse> getMyProcedures();
}
