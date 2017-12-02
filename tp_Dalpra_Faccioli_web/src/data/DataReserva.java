package data;


import util.AppDataException;
import entity.Elemento;
import entity.Persona;
import entity.Reserva;

import java.sql.*;
import java.util.ArrayList;


public class DataReserva{
		
		
	public void add(Reserva r) throws Exception{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"insert into reservas (detalle, estado, id_persona, id_elemento, fecha, hora) values (?,?,?,?,?,?)",
							
							PreparedStatement.RETURN_GENERATED_KEYS
					);
			
			
			stmt.setString(1, r.getDetalle());
			stmt.setBoolean(2, r.getEstado());
			stmt.setInt(3, r.getPersona().getId());
			stmt.setInt(4, r.getElemento().getId());
			stmt.setDate(5, (Date)r.getFecha());
			stmt.setTime(6, (Time)r.getHora());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				r.setId(keyResultSet.getInt(1));
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
	
	public boolean getReservasSuperpuestas(Reserva r) throws Exception{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<Reserva> reservas= new ArrayList<Reserva>();
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"select * from reservas r inner join elementos e on  r.id_elemento=e.id where r.fecha=? and r.hora=? and e.id=?");			
					
			stmt.setDate(1, (Date)r.getFecha());
			stmt.setTime(2, (Time)r.getHora());
			stmt.setInt(3, r.getElemento().getId());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
					Reserva res=new Reserva();
					r.setId(rs.getInt("r.id"));
					reservas.add(res);
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
		
		if(reservas.isEmpty()){return true;}
		else {return false;}
	}
		
	
	public ArrayList<Reserva> getAll() throws Exception{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Reserva> reservas= new ArrayList<Reserva>();
		try {
			stmt = FactoryConexion.getInstancia()
					.getConn().createStatement();
			rs = stmt.executeQuery("select * from reservas r inner join persona p on r.id_persona=p.id inner join elementos e on r.id_elemento=e.id where (r.estado=1) and (r.fecha>CURDATE()) order by fecha, hora");
			if(rs!=null){
				while(rs.next()){
					Reserva r=new Reserva();
					r.setElemento(new Elemento());
					r.setPersona(new Persona());
					r.setId(rs.getInt("r.id"));
					r.getPersona().setNombre(rs.getString("p.nombre"));
					r.getPersona().setApellido(rs.getString("p.apellido"));
					r.getElemento().setNombre(rs.getString("e.nombre"));
					r.setFecha(rs.getDate("r.fecha"));
					r.setHora(rs.getTime("r.hora"));
					r.setEstado(rs.getBoolean("r.estado"));					
					
					reservas.add(r);
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
		
		return reservas;
		
	}


	public void remove(Reserva r) throws Exception {
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"update reservas set estado='0' where id=?"
					);
			stmt.setInt(1, r.getId());
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
	
	

	public ArrayList<Reserva> getByUsuario(Persona per) throws Exception{
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<Reserva> reservas= new ArrayList<Reserva>();
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"select * from reservas r inner join persona p on r.id_persona=p.id inner join elementos e on r.id_elemento= e.id where (p.id=?) and (r.fecha>CURDATE()) and (r.estado=1) order by fecha, hora",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			
			stmt.setInt(1, per.getId());	
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
					Reserva r=new Reserva();
					r.setElemento(new Elemento());
					r.setPersona(new Persona());
					r.setId(rs.getInt("r.id"));
					r.getPersona().setNombre(rs.getString("p.nombre"));
					r.getPersona().setApellido(rs.getString("p.apellido"));
					r.getElemento().setNombre(rs.getString("e.nombre"));
					r.setFecha(rs.getDate("r.fecha"));
					r.setHora(rs.getTime("r.hora"));		
					
					reservas.add(r);
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
		
		return reservas;
		
	}
}
	/*
public String countReservasByUsuario(Reserva r, Persona per) throws Exception{
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String cantidadReservas=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"SELECT COUNT(*) FROM persona p inner join reservas r on p.id=r.id_persona inner join elementos e on r.id_elemento=e.id	inner join tipos_elementos te on e.id_tipo_elemento=te.id where (te.id=?) and (p.id=?)");
			stmt.setInt(1, r.getElemento().getTipo().getId());		
			stmt.setInt(2, per.getId());	
			rs=stmt.executeQuery();
			if(rs!=null){
				cantidadReservas=rs.toString();
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
		
		return cantidadReservas;
		
	}
	*/

		
	
