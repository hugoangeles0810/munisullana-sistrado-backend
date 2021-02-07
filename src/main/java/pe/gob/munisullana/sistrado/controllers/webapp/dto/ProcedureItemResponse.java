package pe.gob.munisullana.sistrado.controllers.webapp.dto;

public class ProcedureItemResponse {

    private Integer id;

    private String numero;

    private String tramite;

    private String estado;

    private String fechaCreacion;

    private String fechaModificacion;

    public ProcedureItemResponse() {
    }

    public ProcedureItemResponse(Integer id, String numero, String tramite, String estado, String fechaCreacion, String fechaModificacion) {
        this.id = id;
        this.numero = numero;
        this.tramite = tramite;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public String getTramite() {
        return tramite;
    }

    public String getEstado() {
        return estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }
}
