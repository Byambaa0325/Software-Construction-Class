package database.writingstrategy;

import java.sql.*;
import database.Database;
import database.DatabaseSavingStrategy;
import database.Entity;

public class MySQLStrategy implements DatabaseSavingStrategy {
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
	public MySQLStrategy(){
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root","root");  
			Statement statement = connect.createStatement();  
			resultSet = statement.executeQuery("select * from customers");  
			while(resultSet.next())  
			System.out.println(resultSet.getInt(1)+"  "+resultSet.getString(2)+"  "+resultSet.getString(3));  
			close();
			}catch(Exception e){ System.out.println(e);}  
			  
	}
	private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

	@Override
	public void save(String filename, Database db) {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity[] read(String filename, Database db) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {
		MySQLStrategy dao = new MySQLStrategy();
	}

}
