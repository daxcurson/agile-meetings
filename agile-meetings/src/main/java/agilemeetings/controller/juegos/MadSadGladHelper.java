package agilemeetings.controller.juegos;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import agilemeetings.dao.TarjetaDAO;

@Strategy(type=agilemeetings.controller.juegos.JuegoHelper.class, juegos={JuegoEnum.MADSADGLAD})
public class MadSadGladHelper implements JuegoHelper
{
	//private static Logger log=LogManager.getLogger(MadSadGladHelper.class);
	@Autowired
	private TarjetaDAO tarjetaDAO;
	@Override
	public ModelAndView proveerModelo(ModelAndView v,int juegoId) 
	{
		v.addObject("mad",tarjetaDAO.getTarjetasMad(juegoId));
		v.addObject("sad",tarjetaDAO.getTarjetasSad(juegoId));
		v.addObject("glad",tarjetaDAO.getTarjetasGlad(juegoId));
		return v;
	}
}
