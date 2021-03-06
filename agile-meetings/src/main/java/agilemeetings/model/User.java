package agilemeetings.model;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

import agilemeetings.model.validacion.PasswordsEqualConstraint;

@Entity
@Table(name="users",uniqueConstraints = @UniqueConstraint(name = "username_uc"
,columnNames = "username"))
@PasswordsEqualConstraint(message = "Los passwords no coinciden")
public class User
{
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	@Column(unique=true)
	private String username;
	private String password;
	@Transient
	private String confirm_password;
	private boolean enabled;
	@OneToOne(targetEntity=Persona.class,cascade={CascadeType.ALL},mappedBy="user")
	@JsonBackReference
	private Persona persona;

	@OneToOne
	private Group group;
	
	public int getId()
	{
		return id;
	}
	public void setId(int i)
	{
		id=i;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String u)
	{
		username=u;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String p)
	{
		password=p;
	}
	public Group getGroup()
	{
		return this.group;
	}
	public void setGroup(Group s)
	{
		this.group=s;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}
