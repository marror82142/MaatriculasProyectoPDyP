package matriculas.models.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long cedula;
	private String nombre;
	private String rol;
	private String profesion;
	private String empresaTrabajo;
	private String nombreUsuario;
	private String contrasena;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contacto_id", referencedColumnName = "id")
	private Contacto infoContacto;
	
	public Contacto getInfoContacto() {
		return infoContacto;
	}

	public void setInfoContacto(Contacto infoContacto) {
		this.infoContacto = infoContacto;
	}

	@Column(name="fecha_nacimiento")
	private LocalDate fechaNacimiento;

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getEmpresaTrabajo() {
		return empresaTrabajo;
	}

	public void setEmpresaTrabajo(String empresaTrabajo) {
		this.empresaTrabajo = empresaTrabajo;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}	
	
}
