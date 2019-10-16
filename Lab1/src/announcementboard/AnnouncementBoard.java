package announcementboard;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import data.Announcement;
import data.Customer;

public class AnnouncementBoard {
	
	private static Map<String,String> announcements;

	//Singleton
	private static final AnnouncementBoard instance = new AnnouncementBoard();
	
	private AnnouncementBoard() {
		announcements = new HashMap<>();
	}
	public static AnnouncementBoard getInstance() {
		return instance;
	}
	public void addAnnouncement(Announcement a, Customer customer){
		announcements.put(a.getContent(), customer.getName());	
	}
	public void printAnnouncements() {
		for(String announcement : announcements.keySet()) {
			System.out.println("Announcement: "+announcement+" by "+announcements.get(announcement));
		}
	}
	public String[] printStrings() {
		String[] toReturn = new String[announcements.size()];
		int i =0;
		for(String announcement : announcements.keySet()) {
			toReturn[i++] = "Announcement: "+announcement+" by "+announcements.get(announcement);
		}
		return toReturn;
	}
}
