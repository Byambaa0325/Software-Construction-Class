package data;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnnouncementBoard {
	//private ArrayList<Announcement> list;
	private static Map<String,String> announcements;
	private static final AnnouncementBoard instance = new AnnouncementBoard();
	private AnnouncementBoard() {
		//this.list= new ArrayList<>();
		announcements = new HashMap<>();
	}
	public static AnnouncementBoard getInstance() {
		return instance;
	}
	public void addAnnouncement(Announcement a, Customer customer){
		announcements.put(a.getContent(), customer.getName());
		//list.add(a);		
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
