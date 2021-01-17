package pe.gob.munisullana.sistrado.events;

import pe.gob.munisullana.sistrado.entities.Ciudadano;

public class UserAppCreatedEvent extends Event {

    private final Ciudadano ciudadano;

    public UserAppCreatedEvent(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }
}
