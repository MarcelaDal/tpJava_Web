package data;

import entity.Persona;
import util.AppDataException;
import entity.Categoria;
import java.sql.*;
import java.util.ArrayList;


public class DataPersona{
		
	public Persona obtenerUsuario(Persona per) throws Exception{
		Persona p= null;
		PreparedStatement stmt= null;
		ResultSet rs= null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from persona where usuario=? and contrasenia=?");
			stmt.setString(1, per.getUsuario());
			stmt.setString(2, per.getContrasenia());
			rs=stmt.executeQuery();
			
			while(rs.next()){
					if(rs!=null){
						p=new Persona();
						p.setCategoria(new Categoria());
						p.setId(rs.getInt("id"));
						p.setNombre(rs.getString("nombre"));
						p.setApellido(rs.getString("apellido"));
						p.setDni(rs.getString("dni"));
						p.setHabilitado(rs.getBoolean("habilitado"));
						p.setUsuario(rs.getString("usuario"));
						p.setContrasenia(rs.getString("contrasenia"));
						p.getCategoria().setId(rs.getInt("id_categoria"));
						
					}
					
			}
				
		} catch (Exception e) {
			
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
		return p;
	}	
	
	
	public void add(Persona p) throws Exception{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"insert into persona(dni, nombre, apellido, habilitado, id_categoria, usuario, contrasenia) values (?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, p.getDni());
			stmt.setString(2, p.getNombre());
			stmt.setString(3, p.getApellido());
			stmt.setBoolean(4, p.isHabilitado());
			stmt.setInt(5, p.getCategoria().getId());
			stmt.setString(6, p.getUsuario());
			stmt.setString(7, p.getContrasenia());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				p.setId(keyResultSet.getInt(1));
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
	
	
	public Persona getByDni(Persona per) throws Exception{
		Persona p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select p.id, nombre, apellido, dni, habilitado, id_categoria, descripcion from persona p inner join categorias_personas c on p.id_categoria=c.id where dni=?");
			stmt.setString(1, per.getDni());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
					p=new Persona();
					p.setCategoria(new Categoria());
					p.setId(rs.getInt("id"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setDni(rs.getString("dni"));
					p.setHabilitado(rs.getBoolean("habilitado"));
					p.getCategoria().setId(rs.getInt("id_categoria"));
					p.getCategoria().setDescripcion(rs.getString("descripcion"));
			}
			
		} catch (Exception e) {
			throw e;
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
		return p;
	}
	
	
	public ArrayList<Persona> getAll() throws Exception{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Persona> pers= new ArrayList<Persona>();
		try {
			stmt = FactoryConexion.getInstancia()
					.getConn().createStatement();
			rs = stmt.executeQuery("select * from persona p inner join categorias_personas c on p.id_categoria=c.id");
			if(rs!=null){
				while(rs.next()){
					Persona p=new Persona();
					p.setCategoria(new Categoria());
					p.setId(rs.getInt("id"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setDni(rs.getString("dni"));
					p.setHabilitado(rs.getBoolean("habilitado"));
					
					p.getCategoria().setId(rs.getInt("id_categoria"));
					p.getCategoria().setDescripcion(rs.getString("descripcion"));
					
					pers.add(p);
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
		
		return pers;
		
	}


	public void remove(Persona p) throws Exception {
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"update persona set habilitado=0 where id=?"
					);
			stmt.setInt(1, p.getId());
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

	public void update(Persona p) throws Exception {
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"update persona set dni=?, nombre=?, apellido=?, id_categoria=?, habilitado=? where id=?"
					);
			stmt.setString(1, p.getDni());
			stmt.setString(2, p.getNombre());
			stmt.setString(3, p.getApellido());
			stmt.setInt(4, p.getCategoria().getId());
			stmt.setBoolean(5, p.isHabilitado());
			stmt.setInt(6, p.getId());
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


		
	
}