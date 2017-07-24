package agilemeetings.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MAD")
public class MadSadGlad extends Juego
{

}
