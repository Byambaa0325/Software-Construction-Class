package databasecontroller;

import database.Command;
import database.Database;
import database.DatabaseSavingStrategy;
import database.Entity;
import database.writingstrategy.TextFileStrategy;

/**
 * Database controller for interacting with database
 */
public class DatabaseController {
	/**
	 * Database to interact
	 */
	private Database db;

	/**
	 * Status if database is databaseLoaded
	 */
	private boolean databaseLoaded = false;

	/**
	 * Strategy for interacting with files
	 * P.S: default is text file
	 */
	private DatabaseSavingStrategy strategy = new TextFileStrategy();

	/**
	 * Name for the database
	 */
	private String databaseName = "db";

	/**
	 * Constructor from database
	 * @param database database to control
	 */
	public DatabaseController(Database database) {
		db = database;
	}

	/**
	 * Execute command received
	 * @param command the command to be executed
	 */
	public void runCommand(Command command) {
		if (!this.databaseLoaded) {
			load();	
		}
		command.execute(db);
		save();
	}

	/**
	 * Write current database entries according to strategy
	 */
	public void save() {
		this.strategy.save(this.databaseName,db);
	}

	/**
	 * Load database according to strategy
	 */
	public void load() {
		System.out.println("Loading Database");
		this.databaseLoaded = true;
		Entity[] data = this.strategy.read(this.databaseName,db);
		if(data == null) {
			System.out.println("No entry in database");
		}
		else {
			for(Entity entity : data) {
				db.insert(entity);
			}
		}
	}

	/**
	 * Change current strategy
	 * @param newStrategy replacing strategy
	 */
	public void changeStrategy(DatabaseSavingStrategy newStrategy) {
		this.strategy = newStrategy;
	}

	/**
	 * Return databaseName
	 * @return the name of the database
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * Change databaseName
	 * @param databaseName replacing database name
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
}
