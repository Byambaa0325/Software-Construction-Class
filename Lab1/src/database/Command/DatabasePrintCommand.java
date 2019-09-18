package database.Command;

import database.Database;
import database.Entity;

public class DatabasePrintCommand implements Command {
	private String commandName = "print";
	@Override
	public void execute(Database db) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return this.commandName;
	}

}
