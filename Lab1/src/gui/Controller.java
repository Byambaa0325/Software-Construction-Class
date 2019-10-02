package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import data.Announcement;
import data.AnnouncementBoard;
import data.Customer;
import database.DatabaseController;
import database.Command.Command;
import database.Command.CommandFactory;

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
			Announcement a = new Announcement(argumentsTextField.getText());
			Customer c = new Customer(id, name);
			board.addAnnouncement(a, c);
			String[] announcements = board.printStrings();
			annTextArea.setText("");
			for(String line : announcements) {
				annTextArea.append(line+"\n");
			}
			return;
		}
		databaseController.runCommand(command);

		model.setDataVector(Model.getData(), Model.getHeaders());     
	}
	private String getCommandName(ActionEvent e) {
		Object o = e.getSource();
		JButton b = null;
		String buttonText = "";

		if(o instanceof JButton)
			b = (JButton)o;

		if(b != null)
			buttonText = b.getText().toLowerCase();
		return buttonText;
	}

}