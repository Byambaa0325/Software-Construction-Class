package database.command;

import database.Command;
import database.Database;
import database.Entity;

public class DatabasePrintCommand implements Command {
	private final String commandName = "print";
	@Override
	public void execute(Database db) {
		
		Entity[] data = db.getData();
		int counter = 0;
		for(Entity entity: data) {
			counter++;
			String[] texts = entity.getObject();
			String line = String.join(", ", texts);
			System.out.println(counter+"-"+line);
		}
	}

	@Override
	public void setArguments(String[] args) {
	}

	@Override
	public String getCommandName() {
		return this.commandName;
	}

}
