package pe.gob.munisullana.sistrado.controllers.backoffice.dto;

public class DerivarSolicitudRequest {

    private int tramiteId;

    public DerivarSolicitudRequest() {
    }

    public DerivarSolicitudRequest(int tramiteId) {
        this.tramiteId = tramiteId;
    }

    public int getTramiteId() {
        return tramiteId;
    }
}
