package pe.gob.munisullana.sistrado.controllers.common.dto;

import java.util.List;

public class ProcedureDetailResponse {

    private Integer id;

    private String numero;

    private String ciudadano;

    private String tramite;

    private String tipo;

    private String estado;

    private String fechaCreacion;

    private String fechaModificacion;

    private List<RequisitoAdjuntoItemResponse> adjuntos;

    public ProcedureDetailResponse(Integer id, String numero, String ciudadano, String tramite, String tipo,
                                   String estado, String fechaCreacion, String fechaModificacion,
                                   List<RequisitoAdjuntoItemResponse> adjuntos) {
        this.id = id;
        this.numero = numero;
        this.ciudadano = ciudadano;
        this.tramite = tramite;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.adjuntos = adjuntos;
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

    public List<RequisitoAdjuntoItemResponse> getAdjuntos() {
        return adjuntos;
    }

    public static class RequisitoAdjuntoItemResponse {

        private Integer id;
        private Integer requisitoId;
        private String adjunto;
        private String fechaCarga;
        private String nombre;
        private String descripcion;
        private String indicaciones;

        public RequisitoAdjuntoItemResponse(Integer id, Integer requisitoId, String adjunto, String fechaCarga, String nombre, String descripcion, String indicaciones) {
            this.id = id;
            this.requisitoId = requisitoId;
            this.adjunto = adjunto;
            this.fechaCarga = fechaCarga;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.indicaciones = indicaciones;
        }

        public Integer getId() {
            return id;
        }

        public Integer getRequisitoId() {
            return requisitoId;
        }

        public String getAdjunto() {
            return adjunto;
        }

        public String getFechaCarga() {
            return fechaCarga;
        }

        public String getNombre() {
            return nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public String getIndicaciones() {
            return indicaciones;
        }
    }
}
