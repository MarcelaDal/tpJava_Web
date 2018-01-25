package data;


import util.AppDataException;
import entity.TipoElementos;
import entity.Elemento;

import java.sql.*;
import java.util.ArrayList;


public class DataElementos{
		
		
	public void add(Elemento ele) throws Exception{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"insert into elementos (nombre, id_tipo_elemento, habilitado) values (?,?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, ele.getNombre());
			stmt.setInt(2, ele.getTipo().getId());
			stmt.setBoolean(3, true);
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				ele.setId(keyResultSet.getInt(1));
			}
			
		} catch (SQLException | AppDataException e) {
			throw e;
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public  ArrayList<Elemento> getByNombre(Elemento ele) throws Exception{
		
		ArrayList<Elemento> elementos= new ArrayList<Elemento>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select e.nombre, e.id, e.id_tipo_elemento, te.nombre, e.habilitado from elementos e inner join tipos_elementos te on e.id_tipo_elemento=te.id where e.nombre like ?");
			stmt.setString(1, '%'+ele.getNombre()+'%');
			rs=stmt.executeQuery();
			
			if(rs!=null){
				while(rs.next()){
					Elemento el=new Elemento();
					el.setTipo(new TipoElementos());
					el.setId(rs.getInt("e.id"));
					el.setNombre(rs.getString("e.nombre"));
					el.getTipo().setId(rs.getInt("e.id_tipo_elemento"));
					el.getTipo().setNombre(rs.getString("te.nombre"));
					el.setHabilitado(rs.getBoolean("e.habilitado"));
					elementos.add(el);
				}		
			}
			
		} catch (Exception ex) {
			throw ex;
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
		return elementos;
	}
	
public Elemento getById(Elemento ele) throws Exception{
		
		Elemento el=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select e.nombre, e.id, e.id_tipo_elemento, te.nombre, e.habilitado from elementos e inner join tipos_elementos te on e.id_tipo_elemento=te.id where e.id = ?");
			stmt.setInt(1, ele.getId());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
					el=new Elemento();
					el.setTipo(new TipoElementos());
					el.setId(rs.getInt("e.id"));
					el.setNombre(rs.getString("e.nombre"));
					el.getTipo().setId(rs.getInt("e.id_tipo_elemento"));
					el.getTipo().setNombre(rs.getString("te.nombre"));
					el.setHabilitado(rs.getBoolean("e.habilitado"));
			}
			
		} catch (Exception ex) {
			throw ex;
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
		return el;
	}
	
	
	public ArrayList<Elemento> getAll() throws Exception{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Elemento> elementos= new ArrayList<Elemento>();
		try {
			stmt = FactoryConexion.getInstancia()
					.getConn().createStatement();
			rs = stmt.executeQuery("select * from elementos e inner join tipos_elementos te on e.id_tipo_elemento=te.id");
			if(rs!=null){
				while(rs.next()){
					Elemento ele=new Elemento();
					ele.setTipo(new TipoElementos());
					ele.setId(rs.getInt("e.id"));
					ele.setNombre(rs.getString("e.nombre"));
					ele.setHabilitado(rs.getBoolean("e.habilitado"));
					ele.getTipo().setId(rs.getInt("e.id_tipo_elemento"));
					ele.getTipo().setNombre(rs.getString("te.nombre"));
					ele.getTipo().setCanMaxResPend(rs.getInt("cant_max_reservas_pendientes"));				
					elementos.add(ele);
				}
			}
		} catch (SQLException e) {
			
			throw e;
		} catch (AppDataException ade){
			throw ade;
		}
		

		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return elementos;
		
	}


	public void remove(Elemento ele) throws Exception {
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"update elementos set habilitado=false where id=?"
					);
			stmt.setInt(1, ele.getId());
			stmt.executeUpdate();
						
			
		} catch (SQLException | AppDataException e) {
			throw e;
		}
		try {
			
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void update(Elemento ele) throws Exception {
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"update elementos set nombre=?, habilitado=? , id_tipo_elemento=? where id=?"
					);
			stmt.setString(1, ele.getNombre());
			stmt.setBoolean(2, ele.isHabilitado());
			stmt.setInt(3, ele.getTipo().getId());
			stmt.setInt(4, ele.getId());
			stmt.executeUpdate();
						
			
		} catch (SQLException | AppDataException e) {
			throw e;
		}
		try {
			
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


public ArrayList<Elemento> getByTipoElemento(TipoElementos te) throws Exception{
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<Elemento> elementos= new ArrayList<Elemento>();
		try {
			stmt = FactoryConexion.getInstancia()
					.getConn().prepareStatement(
					"select * from elementos e inner join tipos_elementos te on e.id_tipo_elemento=te.id where te.id=?");
			stmt.setInt(1, te.getId() );
			rs=stmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Elemento ele=new Elemento();
					ele.setTipo(new TipoElementos());
					ele.setId(rs.getInt("e.id"));
					ele.setNombre(rs.getString("e.nombre"));
					ele.setHabilitado(rs.getBoolean("e.habilitado"));
					ele.getTipo().setNombre(rs.getString("te.nombre"));					
					elementos.add(ele);
				}
			}
		} catch (SQLException e) {
			
			throw e;
		} catch (AppDataException ade){
			throw ade;
		}
		

		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return elementos;
		
	}	
	
}