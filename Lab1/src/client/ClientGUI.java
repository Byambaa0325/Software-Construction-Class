package client;
import javax.swing.SwingUtilities;

import gui.SinglePageGUI;

public class ClientGUI {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    public static void createAndShowGUI() throws Exception {
        new SinglePageGUI();
    }
}
