package agilemeetings.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="roles_usuarios")
public class RolPersona 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="rol_id")
	private Rol rol;
	@ManyToOne
	@JoinColumn(name="persona_id")
	private Persona persona;
	@ManyToOne
	@JoinColumn(name="proyecto_id")
	private Proyecto proyecto;
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
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona usuario) {
		this.persona = usuario;
	}
	@JsonBackReference
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
}
