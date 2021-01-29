package pe.gob.munisullana.sistrado.entities;


import javax.persistence.*;

@Table
@Entity
public class Tramite {

    @Id
    @SequenceGenerator(name = "seq_tramite_id", sequenceName = "seq_tramite_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tramite_id")
    private Integer id;

    @Column(length = 10)
    private String codigo;

    @Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name="tipoid")
    private TramiteTipo tipo;

    @ManyToOne
    @JoinColumn(name="oficinaid")
    private OficinaUnidad oficina;

    @Column
    @Lob
    private String descripcion;

    @Column
    @Lob
    private String indicaciones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TramiteTipo getTipo() {
        return tipo;
    }

    public void setTipo(TramiteTipo tipo) {
        this.tipo = tipo;
    }

    public OficinaUnidad getOficina() {
        return oficina;
    }

    public void setOficina(OficinaUnidad oficina) {
        this.oficina = oficina;
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
}
