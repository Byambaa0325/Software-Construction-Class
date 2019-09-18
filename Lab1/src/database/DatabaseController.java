package database;

import database.Command.Command;
import database.WritingStrategy.DatabaseSavingStrategy;
import database.WritingStrategy.TextFileStrategy;

public class DatabaseController {
	private Database db;
	private boolean loaded = false;
	private DatabaseSavingStrategy strategy = new TextFileStrategy();
	private String databaseName = "db";
	
	public DatabaseController(Database database) {
		db = database;
	}
	public void runCommand(Command command) {
		if (!this.loaded) {
			load();	
		}
		command.execute(db);
		save();
	}
	public void save() {
		this.strategy.save(this.databaseName,db);
	}
	public void load() {
		System.out.println("Loading Database");
		this.loaded = true;
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
	public void changeStrategy(DatabaseSavingStrategy newStrategy) {
		this.strategy = newStrategy;
	}

	public String getDatabaseName() {
		return databaseName;
	}
	
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
}
