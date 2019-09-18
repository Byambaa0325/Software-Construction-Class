package database.Command;

import database.Database;
import database.Entity;

public class DatabaseInsertCommand implements Command {
	private Entity e;
	private String commandName = "insert";
	
	public DatabaseInsertCommand(){}
	public DatabaseInsertCommand(Entity value){
		this.e = value;		
	}
	@Override
	public void execute(Database db) {
		db.insert(e);
	}

	@Override
	public void setArguments(String arg[]) {
		// TODO Auto-generated method stub
		String obj[] = {arg[0],arg[1]};
		e = new Entity(obj);
		
	}
	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return this.commandName;
	}
}
