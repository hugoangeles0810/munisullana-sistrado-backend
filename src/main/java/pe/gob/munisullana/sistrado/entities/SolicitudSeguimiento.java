package pe.gob.munisullana.sistrado.entities;


import javax.persistence.*;
import java.util.Date;

@Table
@Entity
public class SolicitudSeguimiento {

    @Id
    @SequenceGenerator(name = "seq_solicitudseguimiento_id", sequenceName = "seq_solicitudseguimiento_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_solicitudseguimiento_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="solicitudid")
    private Solicitud solicitud;

    @Column
    private String estado;

    @Column
    private String detalle;

    @ManyToOne
    @JoinColumn(name="usuarioid", nullable = true)
    private Usuario usuarioModificacion;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Usuario getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(Usuario usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
