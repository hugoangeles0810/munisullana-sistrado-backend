package pe.gob.munisullana.sistrado.services;

import pe.gob.munisullana.sistrado.controllers.backoffice.dto.*;
import pe.gob.munisullana.sistrado.controllers.common.dto.ProcedureDetailResponse;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.*;
import pe.gob.munisullana.sistrado.entities.Solicitud;

import java.util.List;

public interface SolicitudService {

    CrearSolicitudResponse save(CrearSolicitudRequest request);

    SubsanarSolicitudResponse update(SubsanarSolicitudRequest request);

    List<ProcedureItemResponse> getLoggedCiudadanoProcedures();

    List<ProcedureItemResponse> getLoggedBackofficeProcedures(Solicitud.Estado estado);

    ProcedureDetailResponse getProcedureDetail(Integer id);

    void observarSolicitud(ObservarSolicitudRequest request);

    void derivarSolicitud(DerivarSolicitudRequest request);

    void revisarSolicitud(RevisarSolicitudRequest request);

    void aprobarSolicitud(AprobarSolicitudRequest request);

    GetMetricsResponse getMetrics();
}
