package database.command;

import database.Command;
import database.Database;
import database.Entity;

public class DatabaseSearchCommand implements Command {
	private Entity e;
	private final String commandName = "search";
	@Override
	public void execute(Database db) {
		
		int index = db.search(e);
		if(index>=0) {
			System.out.println("Found at "+index+" index");
		}
		else {
			System.out.println("Entity Not Found");
		}
	}

	@Override
	public void setArguments(String[] args) {
		
		String[] obj = {args[0],args[1]};
		e = new Entity(obj);
	}

	@Override
	public String getCommandName() {
		
		return this.commandName;
	}

}
