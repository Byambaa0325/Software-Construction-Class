package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import announcementboard.AnnouncementBoard;
import data.Announcement;
import data.Customer;
import database.Command;
import database.command.factory.CommandFactory;
import databasecontroller.DatabaseController;

public class Controller implements ActionListener {

	private JTextField argumentsTextField;
	private JTextArea annTextArea;
	private JTable table;
	private DefaultTableModel model;
	private DatabaseController databaseController;
	private CommandFactory commandFactory = new CommandFactory();
	private AnnouncementBoard  board= AnnouncementBoard.getInstance();

	public Controller(JTextField argumentsTextField, JTextArea ann,JTable table, DefaultTableModel model, DatabaseController databaseController) {
		super();
		this.argumentsTextField = argumentsTextField;
		this.annTextArea = ann;
		this.table = table;
		this.model = model;
		this.databaseController = databaseController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String arguments = getCommandName(e)+" "+argumentsTextField.getText();
		String[] args = arguments.split(" ");
		Command command = commandFactory.buildCommand(args);

		if(command == null) {
			int row = table.getSelectedRow();
			if(row == -1) {
				return;
			}
			int id = Integer.parseInt((String) table.getValueAt(row, 0));
			String name = (String) table.getValueAt(row, 1);
			Announcement announcement = new Announcement(argumentsTextField.getText());
			Customer customer = new Customer(id, name);
			board.addAnnouncement(announcement, customer);
			String[] announcements = board.printStrings();
			annTextArea.setText("");
			for(String line : announcements) {
				annTextArea.append(line+"\n");
			}
			return;
		}
		databaseController.runCommand(command);

		model.setDataVector(TableDatabase.getData(), TableDatabase.getHeaders());     
	}
	private String getCommandName(ActionEvent event) {
		Object obj = event.getSource();
		JButton button = null;
		String buttonText = "";

		if(obj instanceof JButton)
			button = (JButton)obj;

		if(button != null)
			buttonText = button.getText().toLowerCase();
		return buttonText;
	}

}