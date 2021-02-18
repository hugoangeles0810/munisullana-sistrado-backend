package pe.gob.munisullana.sistrado.controllers.backoffice.dto;

public class ObservarSolicitudRequest {

    private int tramiteId;
    private String observaciones;

    public ObservarSolicitudRequest() {
    }

    public ObservarSolicitudRequest(int tramiteId, String observaciones) {
        this.tramiteId = tramiteId;
        this.observaciones = observaciones;
    }

    public int getTramiteId() {
        return tramiteId;
    }

    public String getObservaciones() {
        return observaciones;
    }
}
