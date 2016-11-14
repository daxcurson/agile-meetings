package agilemeetings.model;

import javax.persistence.*;

@Entity
@Table(name="usuarios_reuniones")
public class UsuarioReunion 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name="usuario_id")
	private User usuario;
	@ManyToOne
	@JoinColumn(name="reunion_id")
	private Reunion reunion;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	public Reunion getReunion() {
		return reunion;
	}
	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}
}
