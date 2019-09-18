package database.Command;

import database.Database;
import database.Entity;

public class DatabaseSearchCommand implements Command {
	Entity e;
	String commandName = "search";
	@Override
	public void execute(Database db) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		String obj[] = {args[0],args[1]};
		e = new Entity(obj);
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return this.commandName;
	}

}
