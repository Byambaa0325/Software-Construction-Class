package gui;

import javax.swing.table.DefaultTableModel;

import database.Database;
import database.Entity;

@SuppressWarnings("serial")
public class Model extends DefaultTableModel {
	static Database db;
    public Model(Database d) {
   
    	super(getData(d), getHeaders());
    	
        
    }
    public static Object[][] getData() {
    	Entity[] data = db.getData();
    	Object[][] toReturn = new Object[data.length][];
    	for(int i = 0; i < toReturn.length; i++) {
    		toReturn[i] = data[i].getObject();
    	}
    	return toReturn;
    }
    private static Object[][] getData(Database d) {
    	db = d;
    	Entity[] data = db.getData();
    	Object[][] toReturn = new Object[data.length][];
    	for(int i = 0; i < toReturn.length; i++) {
    		toReturn[i] = data[i].getObject();
    	}
    	return toReturn;
    }
    public static Object[] getHeaders() {
    	String[] headers = {"id","name"}; 
    	return headers;
    } 
}