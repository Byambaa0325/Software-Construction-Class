package database.Command;

import database.Database;

public class DatabaseDeleteCommand implements Command {
	int id;
	String commandName = "delete";
	@Override
	public void execute(Database db) {
		// TODO Auto-generated method stub
		db.delete(this.id);
	}

	@Override
	public void setArguments(String[] args) {
		// TODO Auto-generated method stub
		this.id = Integer.parseInt(args[0]);
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return this.commandName;
	}

}
