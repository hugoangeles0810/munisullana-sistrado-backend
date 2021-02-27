package pe.gob.munisullana.sistrado.controllers.common.dto;

import java.util.List;

public class TramiteDetailResponse {

    private Integer id;

    private String codigo;

    private String nombre;

    private String descripcion;

    private String indicaciones;

    private List<RequisitoItemResponse> requisitos;

    public TramiteDetailResponse(Integer id, String codigo, String nombre, String descripcion, String indicaciones, List<RequisitoItemResponse> requisitos) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.indicaciones = indicaciones;
        this.requisitos = requisitos;
    }

    public Integer getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
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

    public List<RequisitoItemResponse> getRequisitos() {
        return requisitos;
    }

    static public class RequisitoItemResponse {

        private Integer id;

        private String nombre;

        private String descripcion;

        private String indicaciones;

        private String tipoAdjunto;

        public RequisitoItemResponse(Integer id, String nombre, String descripcion, String indicaciones, String tipoAdjunto) {
            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.indicaciones = indicaciones;
            this.tipoAdjunto = tipoAdjunto;
        }

        public Integer getId() {
            return id;
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

        public String getTipoAdjunto() {
            return tipoAdjunto;
        }
    }
}
