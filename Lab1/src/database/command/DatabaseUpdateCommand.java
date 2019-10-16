package database.command;

import database.Command;
import database.Database;
import database.Entity;

public class DatabaseUpdateCommand implements Command {
	private Entity a;
	private Entity b;
	private final String commandName = "update";
	@Override
	public void execute(Database db) {
		db.update(a, b);
	}

	@Override
	public void setArguments(String[] args) {
		String[] aObject ={args[0], args[1]};
		String[] bObject ={args[2], args[3]};
		a = new Entity(aObject);
		b = new Entity(bObject);
	}

	@Override
	public String getCommandName() {
		
		return this.commandName;
	}

}
