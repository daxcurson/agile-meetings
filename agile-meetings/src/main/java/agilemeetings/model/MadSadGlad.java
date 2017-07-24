package agilemeetings.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

@Entity
@DiscriminatorValue("MAD")
public class MadSadGlad extends Juego
{
	@OneToMany(targetEntity=Tarjeta.class,
	mappedBy="juego",fetch=FetchType.LAZY,orphanRemoval=true)
	@Cascade(value={org.hibernate.annotations.CascadeType.ALL})
	private List<Tarjeta> tarjetas;

	public List<Tarjeta> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}
}
