package br.com.ex.dbutilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.ex.dbconnection.Connect;

public class CrudCommands {
	
	public static void listUser() {
		int cont = 0;
		try {
			Connection connection = Connect.connect();
			String sqlcommand = "SELECT * FROM dbtable";
			
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sqlcommand);
			
			while(result.next()) {
				String user_id = result.getString(1);
				String name = result.getString(2);
				String pass = result.getString(3);
				String fullname = result.getString(4);
				String email = result.getString(5);
				String output = "User #%s: %s - %s - %s - %s";
				System.out.println(String.format(output, user_id, name, pass, fullname, email));
				cont++;
			}
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		System.out.printf("Total de %d Usuarios", cont);
		
	}
	
	public static void insertUser(int user_id, String username, String password, String fullname, String email) {
		
		try {
			Connection connection = Connect.connect();
			String sqlcommand = "INSERT INTO dbtable (user_id, username, password, fullname, email) VALUES (?,?,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sqlcommand);
			statement.setInt(1, user_id);
			statement.setString(2, username);
			statement.setString(3, password);
			statement.setString(4, fullname);
			statement.setString(5, email);
			
			int rowsInserted = statement.executeUpdate();
			if(rowsInserted > 0) {
				System.out.println("Usuario adicionado com sucesso!");
				connection.close();
			}else {
				System.out.println("\nErro ao inserir usuário\n");
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateUser(int user_id, String username, String password, String fullname, String email) {
		
		
		try {
			Connection connection = Connect.connect();
			String sqlCommand = "UPDATE dbtable SET username = ?, password = ?, fullname = ?, email = ? WHERE user_id = ?";
			
			PreparedStatement statement = connection.prepareStatement(sqlCommand);
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, fullname);
			statement.setString(4, email);
			statement.setInt(5, user_id);
			
			int resultUpdate = statement.executeUpdate();
			
			if(resultUpdate > 0) {
				System.out.println("\nSystem updated");
			} else {
				System.out.println("Failed to update");
			}
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		}
		
	public static void deleteUser(int user_id) {
		
		try {
			
			Connection connection = Connect.connect();
			String sqlCommand = "DELETE FROM dbtable WHERE user_id = ?";
			PreparedStatement statement = connection.prepareStatement(sqlCommand);
			statement.setInt(1, user_id);
			
			int resultUpdate = statement.executeUpdate();
			
			if(resultUpdate > 0) {
				System.out.println("\nUsuario deletado com sucesso");
			}else {
				System.out.println("\nFalha em deletar o usuário");
			}
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	}
	

