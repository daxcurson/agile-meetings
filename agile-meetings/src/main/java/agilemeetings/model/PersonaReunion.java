package agilemeetings.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="personas_reuniones")
public class PersonaReunion 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name="persona_id")
	private Persona persona;
	@ManyToOne
	@JoinColumn(name="reunion_id")
	private Reunion reunion;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	@JsonBackReference
	public Reunion getReunion() {
		return reunion;
	}
	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}
}
