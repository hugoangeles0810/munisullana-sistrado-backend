package pe.gob.munisullana.sistrado.controllers.webapp.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CreateAccountRequest {

    @Email(message = "Debe ingresar un correo válido")
    private String email;

    @Digits(integer = 8, fraction = 0, message = "El DNI debe tener 8 dígitos")
    private String dni;

    @NotBlank(message = "El apellido paterno es obligatorio")
    private String apePaterno;

    @NotBlank(message = "El apellido materno es obligatorio")
    private String apeMaterno;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombres;

    private boolean representante;

    @Digits(integer = 11, fraction = 0, message = "El RUC debe tener 11 dígitos")
    private String ruc;

    private String razonSocial;
    private String dirFiscal;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "La dirección es obligatoria")
    private String departamento;

    @NotBlank(message = "La dirección es obligatoria")
    private String provincia;

    @NotBlank(message = "La dirección es obligatoria")
    private String distrito;

    @NotBlank(message = "La dirección es obligatoria")
    private String celular;

    @Length(min = 8, message = "La contraseña es obligatoria")
    private String clave;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
