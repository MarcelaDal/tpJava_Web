package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.TipoElementos;
import util.AppDataException;

public class DataTipoElementos {
	
	public void add(TipoElementos te) throws Exception{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"insert into tipos_elementos(nombre, cant_max_reservas_pendientes, habilitado) values (?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, te.getNombre());
			stmt.setInt(2, te.getCanMaxResPend());
			stmt.setBoolean(3, true);
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				te.setId(keyResultSet.getInt(1));
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
	
	
		
	
	public ArrayList<TipoElementos> getAll() throws Exception{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<TipoElementos> tipoelementos= new ArrayList<TipoElementos>();
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from tipos_elementos");
			if(rs!=null){
				while(rs.next()){
					TipoElementos te=new TipoElementos();
					te.setId(rs.getInt("id"));
					te.setNombre(rs.getString("nombre"));
					te.setCanMaxResPend(rs.getInt("cant_max_reservas_pendientes"));
					te.setHabilitado(rs.getBoolean("habilitado"));
					
					tipoelementos.add(te);
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
		
		return tipoelementos;
		
	}


	public void remove(TipoElementos te) throws Exception {
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"update tipos_elementos set habilitado=0 where id=?"
					);
			stmt.setInt(1, te.getId());
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

	public void update(TipoElementos te) throws Exception {
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"update tipos_elementos set nombre=?, cant_max_reservas_pendientes=?, habilitado=? where id=?"
					);
			stmt.setString(1, te.getNombre());
			stmt.setInt(2, te.getCanMaxResPend());
			stmt.setBoolean(3, te.isHabilitado());
			stmt.setInt(4, te.getId());
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
	public TipoElementos getByNombre(TipoElementos te) throws Exception{
		TipoElementos ele=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id, nombre, cant_max_reservas_pendientes, habilitado from tipos_elementos  where nombre like ? ");
			stmt.setString(1, '%'+te.getNombre()+'%');
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
					ele=new TipoElementos();
					ele.setId(rs.getInt("id"));
					ele.setNombre(rs.getString("nombre"));
					ele.setCanMaxResPend(rs.getInt("cant_max_reservas_pendientes"));
					ele.setHabilitado(rs.getBoolean("habilitado"));
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
		return ele;
	}
	
}
