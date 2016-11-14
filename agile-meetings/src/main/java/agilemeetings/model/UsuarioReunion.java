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

	@JoinColumn(name="usuario_id")
	private User usuario;
	@JoinColumn(name="reunion_id")
	private Reunion reunion;
}
