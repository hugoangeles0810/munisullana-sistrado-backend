package pe.gob.munisullana.sistrado.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table
public class VerificacionToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Ciudadano.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Ciudadano ciudadano;

    private Date fechaExpiracion;

    public VerificacionToken() {
    }

    public VerificacionToken(String token, Ciudadano ciudadano, int expiryTimeInMinutes) {
        this.token = token;
        this.ciudadano = ciudadano;
        this.fechaExpiracion = calculateExpiryDate(expiryTimeInMinutes);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
