package entity;

import java.io.Serializable;

public class Elemento implements Serializable {
	private int id;
	private String nombre;
	private TipoElementos tipo;
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
	public TipoElementos getTipo() {
		return tipo;
	}
	public void setTipo(TipoElementos tipo) {
		this.tipo = tipo;
	}
	
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	public Elemento(){}
	
	@Override
	public boolean equals(Object e){
		return (e instanceof Elemento && ((Elemento)e).getId()==this.getId());
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
