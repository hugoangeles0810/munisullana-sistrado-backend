package pe.gob.munisullana.sistrado.entities;


import javax.persistence.*;
import java.util.Date;

@Table
@Entity
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 15)
    private String numero;

    @ManyToOne
    @JoinColumn(name="tramiteid")
    private Tramite tramite;

    @ManyToOne
    @JoinColumn(name="ciudadanoid")
    private Ciudadano ciudadano;

    @Column(length = 1)
    private String estado;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
}
