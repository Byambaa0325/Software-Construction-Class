package database;

public interface Command {
	public void execute(Database db);
	public void setArguments(String args[]);
	public String getCommandName();
}
