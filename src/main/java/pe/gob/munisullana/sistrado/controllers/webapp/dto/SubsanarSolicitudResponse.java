package pe.gob.munisullana.sistrado.controllers.webapp.dto;

import pe.gob.munisullana.sistrado.entities.Solicitud;

public class SubsanarSolicitudResponse {

    private String numero;
    private Solicitud.Estado estado;

    public SubsanarSolicitudResponse(String numero, Solicitud.Estado estado) {
        this.numero = numero;
        this.estado = estado;
    }

    public String getNumero() {
        return numero;
    }

    public Solicitud.Estado getEstado() {
        return estado;
    }

}
