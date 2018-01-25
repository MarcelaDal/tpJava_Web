package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Categoria;


public class DataCategoria {
	
	public ArrayList<Categoria> getAll() throws Exception{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Categoria> cats= new ArrayList<Categoria>();
		try{
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from categorias_personas");
			if(rs!=null){
				while(rs.next()){
					Categoria c=new Categoria();
					c.setId(rs.getInt("id"));
					c.setDescripcion(rs.getString("descripcion"));
					cats.add(c);
				}
			}
		} catch (Exception e){
			throw e;
		}
		
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cats;
	}
	
public Categoria getByNombre(Categoria c) throws Exception{
		Categoria cat= null;	
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from categorias_personas where descripcion like ?");
			stmt.setString(1, '%'+c.getDescripcion()+'%');
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
					cat=new Categoria();
					cat.setId(rs.getInt("id"));
					cat.setDescripcion(rs.getString("descripcion"));
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
		return cat;
	}
}