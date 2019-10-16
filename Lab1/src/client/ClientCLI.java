package client;

import java.util.Scanner;

import announcementboard.AnnouncementBoard;
import data.Announcement;
import data.Customer;
import database.Entity;
import database.command.factory.CommandFactory;
import databasecontroller.DatabaseController;
import database.Command;
import database.CustomerDatabase;
import database.Database;

public class ClientCLI {
	static Database db = CustomerDatabase.getInstance();
	static DatabaseController control = new DatabaseController(db);
	static CommandFactory factory = new CommandFactory();
	static Scanner input = new Scanner(System.in);
	static AnnouncementBoard board = AnnouncementBoard.getInstance();

	public static void main(String[] args) {

		boolean exit = false;
		while(!exit) {
			report();
			String arguments = input.nextLine();
			
			//Check if special case announce command
			if (arguments.equals("announce")) {
				control.load();
				addAnnouncement();
				continue;
			}
			
			//Build Command
			Command command = factory.buildCommand(arguments.split(" "));
			
			//Unsuccessful attempt to build command
			if (command == null) {
				//Confirm exit
				System.out.println("Command not recognized...");
				System.out.println("Exit(Y/N)?");
				arguments = input.nextLine();
				
				//Confirmed and exit
				if(arguments.startsWith("Y")){exit = true;}
			}
			//Run the command
			else {
				control.runCommand(command);
			}
		}
		System.out.println("Exiting");
	}
	private static void addAnnouncement() {
		System.out.println("Write the announcement below");
		String content = input.nextLine();

		System.out.println("Input the database index of the announcer");
		int index = Integer.parseInt(input.nextLine());

		Announcement ann = new Announcement(content);
		
		if(index > db.getData().length) {
			System.out.println("index not found in database");
			return;
		}
		Entity entity = db.getData()[index-1];
		String object[] = entity.getObject();
		Customer customer = new Customer(Integer.parseInt(object[0]),object[1]);

		board.addAnnouncement(ann, customer);
		
	}
	private static void report() {
		System.out.println("Announcement boards Commands");
		board.printAnnouncements();
		System.out.println("announce");
		System.out.println("Database Commands");
		factory.printCommands();
	}
}
