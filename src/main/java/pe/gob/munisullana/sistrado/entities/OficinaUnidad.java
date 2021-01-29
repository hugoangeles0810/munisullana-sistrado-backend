package pe.gob.munisullana.sistrado.entities;


import javax.persistence.*;

@Table
@Entity
public class OficinaUnidad {

    @Id
    @SequenceGenerator(name = "seq_oficinaunidad_id", sequenceName = "seq_oficinaunidad_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_oficinaunidad_id")
    private int id;

    @Column(length = 6)
    private String codigo;

    @Column
    private String nombre;

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
}
