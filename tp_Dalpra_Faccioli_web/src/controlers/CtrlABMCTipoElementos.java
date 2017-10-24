package controlers;

import java.util.ArrayList;

import data.DataCategoria;
import data.DataPersona;
import data.DataTipoElementos;
import entity.Categoria;
import entity.Persona;
import entity.TipoElementos;

public class CtrlABMCTipoElementos {
	private DataTipoElementos dataTipoE;
	
	public CtrlABMCTipoElementos(){
		dataTipoE = new DataTipoElementos();
	}
	
	public void add(TipoElementos te) throws Exception{
		dataTipoE.add(te);
	}
	
	public void delete(TipoElementos te)throws Exception{
		dataTipoE.remove(te);
	}
	
	public void update(TipoElementos te)throws Exception{
		dataTipoE.update(te);
	}
	
		
	public ArrayList<TipoElementos> getAll()throws Exception{
		return dataTipoE.getAll();
	}
	
	public TipoElementos getByNombre(TipoElementos te) throws Exception{
		return this.dataTipoE.getByNombre(te);
	}
	
	public TipoElementos getByNombre(String nombre)throws Exception{
		TipoElementos te=new TipoElementos();
		te.setNombre(nombre);
		return getByNombre(te);
	}
	
}
