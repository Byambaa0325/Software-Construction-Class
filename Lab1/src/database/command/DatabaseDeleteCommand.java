package database.command;

import database.Command;
import database.Database;

public class DatabaseDeleteCommand implements Command {
	private int id;
	private final String commandName = "delete";

	@Override
	public void execute(Database db) {
		db.delete(this.id);
	}

	@Override
	public void setArguments(String[] args) {
		this.id = Integer.parseInt(args[0]);
	}

	@Override
	public String getCommandName() {
		return this.commandName;
	}

}
