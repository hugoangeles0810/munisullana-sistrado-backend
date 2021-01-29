package pe.gob.munisullana.sistrado.entities;

import javax.persistence.*;

@Table
@Entity
public class TramiteTipo {

    @Id
    @SequenceGenerator(name = "seq_tramitetipo_id", sequenceName = "seq_tramitetipo_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tramitetipo_id")
    private Integer id;

    @Column(length = 10)
    private String codigo;

    @Column
    private String nombre;

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
}
