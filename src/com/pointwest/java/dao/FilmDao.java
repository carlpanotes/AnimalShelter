package com.pointwest.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.pointwest.java.bean.FilmBean;

public class FilmDao {
	
	Logger logger = Logger.getLogger(FilmDao.class);
	
	public List<FilmBean> getFilms() {
		List<FilmBean> films = null;
		String db = "jdbc:mysql://172.26.83.193:3306/sakila";
		String user = "newuser";
		String password = "password123";
		try {
			Connection conn = DriverManager.getConnection(db,user,password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM sakila.film");
			
			films = new ArrayList<FilmBean>();
			while(rs.next()) {
				FilmBean film = new FilmBean();
				film.setDescription(rs.getString("description"));
				film.setTitle(rs.getString("title"));
				film.setLength(rs.getInt("length"));
				films.add(film);
			}
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from database.");
			//logger.error(e1.printStackTrace());
		} finally {
			
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return films;
	}
	
	public List<FilmBean> getSpecificFilm() {
		List<FilmBean> films = null;
		String db = "jdbc:mysql://172.26.83.193:3306/sakila";
		String user = "newuser";
		String password = "password123";
		try {
			Connection conn = DriverManager.getConnection(db,user,password);
			Statement statement = conn.createStatement();
			String filmZoo = "zoolander fiction";
			ResultSet rs = statement.executeQuery("SELECT * FROM sakila.film WHERE title = \"" + filmZoo + "\"");
			
			films = new ArrayList<FilmBean>();
			while(rs.next()) {
				FilmBean film = new FilmBean();
				film.setDescription(rs.getString("description"));
				film.setTitle(rs.getString("title"));
				film.setLength(rs.getInt("length"));
				films.add(film);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from database.");
			//logger.error(e1.printStackTrace());
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return films;
	}
	
	public void closeConnection(Connection conn, Statement statement, ResultSet rs) {
		try {
			if(conn != null) {
				conn.close();
			}
			if(rs != null) {
				rs.close();
			}
			if(statement != null) {
				statement.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<FilmBean> searchFilm() {
		List<FilmBean> films = null;
		String db = "jdbc:mysql://172.26.83.193:3306/sakila";
		String user = "newuser";
		String password = "password123";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			conn = DriverManager.getConnection(db,user,password);
			//Statement statement = conn.createStatement();
			//String filmZoo = "zoolander fiction";
			//ResultSet rs = statement.executeQuery("SELECT * FROM sakila.film WHERE title like \"" + filmZoo + "\"");
			
			//String query = "SELECT * FROM sakila.film WHERE film.title like ?";
			String query = "SELECT * FROM sakila.film WHERE film.length > ? AND film.length < ?";
			ps = conn.prepareStatement(query);
			
			//ps.setString(1, "%the%");
			ps.setInt(1, 100);
			ps.setInt(2, 150);
			rs = ps.executeQuery();
			
			films = new ArrayList<FilmBean>();
			while(rs.next()) {
				FilmBean film = new FilmBean();
				film.setDescription(rs.getString("description"));
				film.setTitle(rs.getString("title"));
				film.setLength(rs.getInt("length"));
				films.add(film);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from database.");
			//logger.error(e1.printStackTrace());
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(conn, ps, rs);
		}
		return films;
	}
	
}
