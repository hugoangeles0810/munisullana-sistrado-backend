package pe.gob.munisullana.sistrado.entities;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity
public class SolicitudAdjunto {

    @Id
    @SequenceGenerator(name = "seq_solicitudadjunto_id", sequenceName = "seq_solicitudadjunto_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_solicitudadjunto_id")
    private int id;

    @ManyToOne
    @JoinColumn(name="solicitudid")
    private Solicitud solicitud;

    @ManyToOne
    @JoinColumn(name="requisitoid")
    private Requisito requisito;

    @Column
    private String adjunto;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCarga;

    public SolicitudAdjunto() {
    }

    public SolicitudAdjunto(Solicitud solicitud, Requisito requisito, String adjunto, Date fechaCarga) {
        this.solicitud = solicitud;
        this.requisito = requisito;
        this.adjunto = adjunto;
        this.fechaCarga = fechaCarga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Requisito getRequisito() {
        return requisito;
    }

    public void setRequisito(Requisito requisito) {
        this.requisito = requisito;
    }

    public String getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }
}
