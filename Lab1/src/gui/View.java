package gui;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import database.Database;
import database.DatabaseController;
import database.Command.CustomerDatabase;

public class View {
	public View() {
		Database db = new CustomerDatabase();
		DatabaseController control = new DatabaseController(db);
		control.load();

		// Create views swing UI components 
		JTextField argumentsTextField = new JTextField(26);
		JTextArea announcements = new JTextArea(20,26);
		announcements.setEditable(false);

		JButton insertButton = new JButton("Insert");
		JButton deleteButton = new JButton("Delete");
		JButton updateButton = new JButton("Update");
		JButton searchButton = new JButton("Search");
		JButton announceButton = new JButton("Announce");
		JTable table = new JTable();

		// Create table model
		Model model = new Model(db);
		table.setModel(model);

		// Create controller
		Controller controller = new Controller(argumentsTextField,announcements,table, model, control);

		insertButton.addActionListener(controller);
		searchButton.addActionListener(controller);
		deleteButton.addActionListener(controller);
		updateButton.addActionListener(controller);
		announceButton.addActionListener(controller);

		// Set the view layout
		JPanel ctrlPane = new JPanel();
		ctrlPane.add(argumentsTextField);
		ctrlPane.add(insertButton);
		ctrlPane.add(updateButton);
		ctrlPane.add(deleteButton);
		ctrlPane.add(searchButton);
		ctrlPane.add(announceButton);



		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setPreferredSize(new Dimension(700, 182));
		tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Customers",
				TitledBorder.CENTER, TitledBorder.TOP));

		JScrollPane annScrollPane = new JScrollPane(announcements);
		annScrollPane.setPreferredSize(new Dimension(700, 182));
		annScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Announcements",
				TitledBorder.CENTER, TitledBorder.TOP));
		JSplitPane splitPaneBottom = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tableScrollPane, announcements);
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrlPane, splitPaneBottom);
		splitPane.setDividerLocation(35);
		splitPane.setEnabled(false);

		// Display it all in a scrolling window and make the window appear
		JFrame frame = new JFrame("Swing MVC Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(splitPane);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}