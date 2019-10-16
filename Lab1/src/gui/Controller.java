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
	private AnnouncementBoard  board = AnnouncementBoard.getInstance();

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
		String[] args = (getCommandName(e)+" "+argumentsTextField.getText()).split(" ");
		Command command = commandFactory.buildCommand(args);

		if(command == null) {
			announceCommand();
			return;
		}
		databaseController.runCommand(command);

		model.setDataVector(DatabaseTableView.getData(), DatabaseTableView.getHeaders());     
	}

	private void announceCommand() {
		int row = table.getSelectedRow();
		if(row == -1) {
			return;
		}
		updateBoardByRow(row);
		updateAnnTextArea();
	}

	private void updateBoardByRow(int row) {
		Customer customer = buildCustomer(row);
		Announcement announcement = getAnnouncement();
		board.addAnnouncement(announcement, customer);
	}

	private void updateAnnTextArea() {
		String[] announcements = board.printStrings();
		annTextArea.setText("");
		for(String line : announcements) {
			annTextArea.append(line+"\n");
		}
	}

	private Customer buildCustomer(int row) {
		int id = Integer.parseInt((String) table.getValueAt(row, 0));
		String name = (String) table.getValueAt(row, 1);
		Customer customer = new Customer(id, name);
		return customer;
	}
	private Announcement getAnnouncement() {
		return new Announcement(argumentsTextField.getText());
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