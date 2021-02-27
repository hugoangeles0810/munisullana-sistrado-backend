package pe.gob.munisullana.sistrado.events;

import pe.gob.munisullana.sistrado.entities.Solicitud;
import pe.gob.munisullana.sistrado.entities.SolicitudSeguimiento;

public class SolicitudUpdatedEvent extends  Event {

    private SolicitudSeguimiento solicitud;

    public SolicitudUpdatedEvent(SolicitudSeguimiento solicitud) {
        this.solicitud = solicitud;
    }

    public SolicitudSeguimiento getSolicitud() {
        return solicitud;
    }
}
