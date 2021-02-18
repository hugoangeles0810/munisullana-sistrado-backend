package pe.gob.munisullana.sistrado.events;

import pe.gob.munisullana.sistrado.entities.Solicitud;
import pe.gob.munisullana.sistrado.entities.SolicitudSeguimiento;

public class SolicitudUpdtedEvent extends  Event {

    private SolicitudSeguimiento solicitud;

    public SolicitudUpdtedEvent(SolicitudSeguimiento solicitud) {
        this.solicitud = solicitud;
    }

    public SolicitudSeguimiento getSolicitud() {
        return solicitud;
    }
}
