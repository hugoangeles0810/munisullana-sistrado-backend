package pe.gob.munisullana.sistrado.entities;


import javax.persistence.*;
import java.util.Date;

@Table
@Entity
public class Ciudadano {

    public enum Estado {
        PENDIENTE,ACTIVO,BLOQUEADO;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String nombre;

    @Column(nullable = false)
    private String apePaterno;

    @Column(nullable = false)
    private String apeMaterno;

    @Column(unique = true)
    private String numeroDocumento;

    @Column(length = 15, nullable = true)
    private String telefono;

    @Column(unique = true)
    private String email;

    @Column
    private String clave;

    @Column
    private boolean representante;

    @Column(length = 11, nullable = true)
    private String ruc;

    @Column(nullable = true)
    private String razonSocial;

    @Column(nullable = true)
    private String dirFiscal;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isRepresentante() {
        return representante;
    }

    public void setRepresentante(boolean representante) {
        this.representante = representante;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDirFiscal() {
        return dirFiscal;
    }

    public void setDirFiscal(String dirFiscal) {
        this.dirFiscal = dirFiscal;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
