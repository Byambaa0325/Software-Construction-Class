package database.command.factory;

import java.util.Arrays;

import database.Command;
import database.command.DatabaseDeleteCommand;
import database.command.DatabaseInsertCommand;
import database.command.DatabasePrintCommand;
import database.command.DatabaseSearchCommand;
import database.command.DatabaseUpdateCommand;

public class CommandFactory {
	/**
	 * Command that can be produced
	 */
	static private Command[] availableCommands = {
			new DatabaseInsertCommand(), 
			new DatabaseDeleteCommand(),
			new DatabaseSearchCommand(),
			new DatabaseUpdateCommand(),
			new DatabasePrintCommand() 
			};

	/**
	 * Default Constructor
	 */
	public CommandFactory(){
		
	}
	public Command buildCommand(String[] args) {
		for(Command command: availableCommands) {
			if(command.getCommandName().equals(args[0])){
				command.setArguments(Arrays.copyOfRange(args,1,args.length));
				return command;
			}
		}
		return null;		
	}

	/**
	 * Report possible commands
	 */
	public void printCommands() {
		for(Command command: availableCommands) {
			System.out.println(command.getCommandName());
		}
	}
}
