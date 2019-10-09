package admin;

import java.util.Scanner;

import data.Announcement;
import data.AnnouncementBoard;
import data.Customer;
import database.DatabaseController;
import database.Entity;
import database.Command.Command;
import database.Command.CommandFactory;
import database.CustomerDatabase;

public class Admin {
	static CustomerDatabase db = new CustomerDatabase();
	static DatabaseController control = new DatabaseController(db);
	static CommandFactory factory = new CommandFactory();
	static Scanner input = new Scanner(System.in);
	static AnnouncementBoard board = AnnouncementBoard.getInstance();

	public static void main(String[] args) {

		boolean exit = false;
		while(!exit) {
			report();
			String arguments = input.nextLine();
			if (arguments.equals("announce")) {
				control.load();
				addAnnouncement();
				continue;
			}
			Command command = factory.buildCommand(arguments.split(" "));
			if (command == null) {
				System.out.println("Command not recognized...");
				System.out.println("Exit(Y/N)?");
				arguments = input.nextLine();
				if(arguments.startsWith("Y")){
					exit = true;
				}
			}else {
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
