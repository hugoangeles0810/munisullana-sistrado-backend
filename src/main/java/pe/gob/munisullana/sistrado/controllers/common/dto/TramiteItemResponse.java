package pe.gob.munisullana.sistrado.controllers.common.dto;

public class TramiteItemResponse {

    private Integer id;

    private String codigo;

    private String nombre;

    private String descripcion;

    private String indicaciones;

    public TramiteItemResponse(Integer id, String codigo, String nombre, String descripcion, String indicaciones) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.indicaciones = indicaciones;
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
}
