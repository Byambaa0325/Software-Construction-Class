package database.Command;

import database.Database;
import database.Entity;

public class DatabaseUpdateCommand implements Command {
	Entity a;
	Entity b;
	String commandName = "update";
	@Override
	public void execute(Database db) {
		// TODO Auto-generated method stub
		db.update(a, b);
	}

	@Override
	public void setArguments(String[] args) {
		// TODO Auto-generated method stub
		String aObject[] ={args[0], args[1]};
		String bObject[] ={args[2], args[3]};
		a = new Entity(aObject);
		b = new Entity(bObject);
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return this.commandName;
	}

}
