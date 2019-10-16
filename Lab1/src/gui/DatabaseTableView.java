package gui;

import javax.swing.table.DefaultTableModel;

import database.Database;
import database.Entity;

@SuppressWarnings("serial")
public class DatabaseTableView extends DefaultTableModel {
	/**
	 * Headers for current model
	 */
	private static final String[] HEADERS = {"id", "name"};
	/**
	 * Database for the model
	 */
	static Database db;

	/**
	 * Constructor
	 * @param d database to construct from
	 */
	public DatabaseTableView(Database d) {
		super(getData(d), getHeaders());
	}

	/**
	 * Retrieve data from database
	 * @return the data stored
	 */
	public static Object[][] getData() {
		Entity[] data = db.getData();
		Object[][] toReturn = new Object[data.length][];
		for(int i = 0; i < toReturn.length; i++) {
			toReturn[i] = data[i].getObject();
		}
		return toReturn;
	}

	/**
	 * Retrieve from argument database
	 * @param d database to get from
	 * @return the data stored in d
	 */
	private static Object[][] getData(Database d) {
		db = d;
		Entity[] data = db.getData();
		Object[][] toReturn = new Object[data.length][];
		for(int i = 0; i < toReturn.length; i++) {
			toReturn[i] = data[i].getObject();
		}
		return toReturn;
	}

	/**
	 * Return headers
	 * @return the headers
	 */
	public static Object[] getHeaders() {
		return HEADERS;
	}
}