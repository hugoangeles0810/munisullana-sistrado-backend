package pe.gob.munisullana.sistrado.services;

import pe.gob.munisullana.sistrado.entities.SolicitudSeguimiento;

public interface SolicitudSeguimientoService {

    void sendMailSolicitudUpdated(SolicitudSeguimiento solicitudSeguimiento);

    void sendMailSolicitudCreated(SolicitudSeguimiento solicitudSeguimiento);
}
