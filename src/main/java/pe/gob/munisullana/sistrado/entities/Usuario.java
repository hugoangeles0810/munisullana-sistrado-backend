package pe.gob.munisullana.sistrado.entities;


import javax.persistence.*;
import java.util.Date;

@Table
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="roleid")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name="oficinaid")
    private OficinaUnidad oficina;

    @Column
    private String nombre;

    @Column
    private String apellidos;

    @Column
    private String email;

    @Column
    private String clave;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public OficinaUnidad getOficina() {
        return oficina;
    }

    public void setOficina(OficinaUnidad oficina) {
        this.oficina = oficina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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
}
