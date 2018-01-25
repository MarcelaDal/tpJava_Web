package controlers;

import java.io.Serializable;
import java.util.ArrayList;

import data.DataTipoElementos;
import data.DataElementos;
import entity.TipoElementos;
import entity.Elemento;

public class CtrlABMCElementos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private DataElementos dataElementos;
	private DataTipoElementos dataTipoEle;
	
	
	public CtrlABMCElementos(){
		dataElementos = new DataElementos();
		dataTipoEle = new DataTipoElementos();
	}
	
	public void add(Elemento ele) throws Exception{
		dataElementos.add(ele);
	}
	
	public void delete(Elemento ele)throws Exception{
		dataElementos.remove(ele);
	}
	
	public void update(Elemento ele)throws Exception{
		dataElementos.update(ele);
	}
	
	public Elemento getByNombre(Elemento el) throws Exception{
		return this.dataElementos.getByNombre(el);
	}
	
	public Elemento getById(int id) throws Exception{
		Elemento el= new Elemento(); 
		el.setId(id);
		return this.dataElementos.getById(el);
	}
	
	public Elemento getByNombre(String nombre)throws Exception{
		Elemento ele=new Elemento();
		ele.setNombre(nombre);
		return getByNombre(ele);
	}
	
		
	public ArrayList<Elemento> getAll()throws Exception{
		return dataElementos.getAll();
	}
	
	public ArrayList<TipoElementos> getTipoElementos() throws Exception{
		return dataTipoEle.getAll();
	}
	
}