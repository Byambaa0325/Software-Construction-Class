package database.Command;

import database.Database;

public interface Command {
	public void execute(Database db);
	public void setArguments(String args[]);
	public String getCommandName();
}
