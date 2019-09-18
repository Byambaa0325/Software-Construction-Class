package database.Command;

import java.util.Arrays;

public class CommandFactory {
	static private Command availableCommands[] = {
			new DatabaseInsertCommand(), 
			new DatabaseDeleteCommand(),
			new DatabaseSearchCommand(),
			new DatabaseUpdateCommand(),
			new DatabasePrintCommand() 
			};
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
	public void printCommands() {
		for(Command command: availableCommands) {
			System.out.println(command.getCommandName());
		}
	}
}
