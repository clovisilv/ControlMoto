package br.com.cs.controlmoto.domain;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.cs.controlmoto.utils.LerArquivo;

public class ConnectionFactory extends LerArquivo{
	/**
	 * 
	 */
	public static java.lang.String caminho="", separadorPath="";
	/**
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws InstantiationException, IllegalAccessException{
		//Variaveis para armazenar o caminho do path e o tipo de separador de pasta do sistema operacional
		caminho = new File("").getAbsolutePath();
		separadorPath = System.getProperty("file.separator");
		separadorPath = separadorPath.replace("\\", "/"); 
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			return DriverManager.getConnection("jdbc:mysql:"+separadorPath+separadorPath+"localhost:3306"+separadorPath+"controlmotoserver", "ControlMoto", "cmdbsv");
		}catch (SQLException e) {
			throw new RuntimeException();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException(); 
		}

	}
	
	/**
	 * 
	 */
	public static void getClosedConnection() throws InstantiationException, IllegalAccessException, SQLException{
//		try {
			getConnection().close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}		
	}

}
