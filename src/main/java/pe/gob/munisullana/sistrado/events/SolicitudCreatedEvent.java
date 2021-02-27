package pe.gob.munisullana.sistrado.events;

import pe.gob.munisullana.sistrado.entities.SolicitudSeguimiento;

public class SolicitudCreatedEvent extends Event {

    private SolicitudSeguimiento solicitud;

    public SolicitudCreatedEvent(SolicitudSeguimiento solicitud) {
        this.solicitud = solicitud;
    }

    public SolicitudSeguimiento getSolicitud() {
        return solicitud;
    }
}
