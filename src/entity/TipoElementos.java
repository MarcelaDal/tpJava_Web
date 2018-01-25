package entity;

import java.io.Serializable;

public class TipoElementos implements Serializable {
	private int id;
	private String nombre;
	private int canMaxResPend;
	private boolean habilitado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCanMaxResPend() {
		return canMaxResPend;
	}
	public void setCanMaxResPend(int canMaxResPend) {
		this.canMaxResPend = canMaxResPend;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	@Override
	public boolean equals(Object o){
		return (o instanceof TipoElementos && ((TipoElementos)o).getId()==this.getId());
	}
	
	@Override
	public int hashCode(){
		return ((Integer)this.getId()).hashCode();
	}
	@Override
	public String toString(){
		return this.getNombre();
	}
}
