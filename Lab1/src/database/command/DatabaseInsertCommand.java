package database.command;

import database.Command;
import database.Database;
import database.Entity;

public class DatabaseInsertCommand implements Command {
	private Entity e;
	private final String commandName = "insert";
	
	public DatabaseInsertCommand(){}
	public DatabaseInsertCommand(Entity value){
		this.e = value;		
	}
	@Override
	public void execute(Database db) {
		db.insert(e);
	}

	@Override
	public void setArguments(String[] arg) {
		
		String[] obj = {arg[0],arg[1]};
		e = new Entity(obj);
		
	}
	@Override
	public String getCommandName() {
		
		return this.commandName;
	}
}
