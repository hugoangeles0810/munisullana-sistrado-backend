package pe.gob.munisullana.sistrado.entities;


import javax.persistence.*;

@Table
@Entity
public class Tramite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
