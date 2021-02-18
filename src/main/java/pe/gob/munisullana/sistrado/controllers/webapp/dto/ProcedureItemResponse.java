package pe.gob.munisullana.sistrado.controllers.webapp.dto;

public class ProcedureItemResponse {

    private Integer id;

    private String numero;

    private String ciudadano;

    private String tramite;

    private String tipo;

    private String estado;

    private String fechaCreacion;

    private String fechaModificacion;

    public ProcedureItemResponse() {
    }

    public ProcedureItemResponse(Integer id, String numero, String ciudadano, String tramite, String tipo, String estado, String fechaCreacion, String fechaModificacion) {
        this.id = id;
        this.numero = numero;
        this.ciudadano = ciudadano;
        this.tramite = tramite;
        this.tipo = tipo;
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

    public String getCiudadano() {
        return ciudadano;
    }

    public String getTramite() {
        return tramite;
    }

    public String getTipo() {
        return tipo;
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
