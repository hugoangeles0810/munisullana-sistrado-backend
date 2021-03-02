package pe.gob.munisullana.sistrado.controllers.backoffice.dto;

public class RevisarSolicitudRequest {
    private int tramiteId;

    public RevisarSolicitudRequest() {
    }

    public RevisarSolicitudRequest(int tramiteId) {
        this.tramiteId = tramiteId;
    }

    public int getTramiteId() {
        return tramiteId;
    }
}
