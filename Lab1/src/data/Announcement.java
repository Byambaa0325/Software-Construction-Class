package data;

public class Announcement {
	public String content;
	public Announcement(String text) {
		this.content = text;
	}
	public Announcement() {
		this.content = "This is a default announcement";
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
