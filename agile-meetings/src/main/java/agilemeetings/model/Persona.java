package agilemeetings.model;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="personas")
public class Persona 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// La persona puede tener cero o un usuario en el sistema.
	@OneToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="user_id")
	private User user;
	private int usuario_sistema;
	private String nombre;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getUsuario_sistema() {
		return usuario_sistema;
	}
	public void setUsuario_sistema(int usuario_sistema) {
		this.usuario_sistema = usuario_sistema;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
