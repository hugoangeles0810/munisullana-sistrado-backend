package pe.gob.munisullana.sistrado.controllers.backoffice.dto;

public class GetMetricsResponse {

    private int pendientes;
    private int observados;
    private int aprobados;

    public GetMetricsResponse(int pendientes, int observados, int aprobados) {
        this.pendientes = pendientes;
        this.observados = observados;
        this.aprobados = aprobados;
    }

    public int getPendientes() {
        return pendientes;
    }

    public int getObservados() {
        return observados;
    }

    public int getAprobados() {
        return aprobados;
    }
}
