package pe.gob.munisullana.sistrado.controllers.backoffice.dto;

public class AprobarSolicitudRequest {

    private int tramiteId;

    public AprobarSolicitudRequest() {
    }

    public AprobarSolicitudRequest(int tramiteId) {
        this.tramiteId = tramiteId;
    }

    public int getTramiteId() {
        return tramiteId;
    }
}
