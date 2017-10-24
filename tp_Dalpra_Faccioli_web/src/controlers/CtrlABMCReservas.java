package controlers;

import java.util.ArrayList;

import data.DataElementos;
import data.DataPersona;
import data.DataReserva;
import data.DataTipoElementos;
import entity.Elemento;
import entity.Persona;
import entity.Reserva;
import entity.TipoElementos;

public class CtrlABMCReservas {
	
	private DataReserva dataReserva;
	private DataElementos dataElementos;
	private DataTipoElementos dataTipoEle;
	
	
	public CtrlABMCReservas(){
		dataReserva = new DataReserva();
		dataElementos = new DataElementos();
		dataTipoEle = new DataTipoElementos();
		
	}
	
	
	public void add(Reserva r) throws Exception{
		dataReserva.add(r);
	}
	
	public void delete(Reserva r)throws Exception{
		dataReserva.remove(r);
	}
	
	public ArrayList<Elemento> getByTipoElemento(TipoElementos te) throws Exception{
		return dataElementos.getByTipoElemento(te);
	}
	
	public ArrayList<Reserva> getByUsuario(Persona p) throws Exception{
		return dataReserva.getByUsuario(p);
	}
		
	public ArrayList<Reserva> getAll()throws Exception{
		return dataReserva.getAll();
	}
	
	public ArrayList<TipoElementos> getTipoElementos() throws Exception{
		return dataTipoEle.getAll();
	}
	public ArrayList<Elemento> getElementos() throws Exception{
		return dataElementos.getAll();
	}
	public boolean isDateTimeAvailable(Reserva r) throws Exception{
		return dataReserva.getReservasSuperpuestas(r);
	}
	/*public int countReservasByUsuario(Reserva r, Persona p) throws Exception{
		return Integer.parseInt(dataReserva.countReservasByUsuario(r,p));
	}*/
}