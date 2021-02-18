package pe.gob.munisullana.sistrado.services;

import pe.gob.munisullana.sistrado.controllers.backoffice.dto.ObservarSolicitudRequest;
import pe.gob.munisullana.sistrado.controllers.common.dto.ProcedureDetailResponse;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.CrearSolicitudRequest;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.CrearSolicitudResponse;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.ProcedureItemResponse;

import java.util.List;

public interface SolicitudService {

    CrearSolicitudResponse save(CrearSolicitudRequest request);

    List<ProcedureItemResponse> getLoggedCiudadanoProcedures();

    List<ProcedureItemResponse> getLoggedBackofficeProcedures();

    ProcedureDetailResponse getProcedureDetail(Integer id);

    void observarSolicitud(ObservarSolicitudRequest request);
}
