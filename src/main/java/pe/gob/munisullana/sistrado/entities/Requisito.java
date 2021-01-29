package pe.gob.munisullana.sistrado.entities;


import javax.persistence.*;

@Table
@Entity
public class Requisito {

    @Id
    @SequenceGenerator(name = "seq_requisito_id", sequenceName = "seq_requisito_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_requisito_id")
    private int id;

    @ManyToOne
    @JoinColumn(name="tramiteid")
    private Tramite tramite;

    @Column
    private String nombre;

    @Column
    @Lob
    private String descripcion;

    @Column
    @Lob
    private String indicaciones;

    @Column
    private String tipoAdjunto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getTipoAdjunto() {
        return tipoAdjunto;
    }

    public void setTipoAdjunto(String tipoAdjunto) {
        this.tipoAdjunto = tipoAdjunto;
    }
}
