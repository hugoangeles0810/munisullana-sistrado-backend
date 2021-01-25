package pe.gob.munisullana.sistrado.entities;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity
public class SolicitudAdjunto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
