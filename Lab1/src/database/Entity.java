package database;

public class Entity{
	private String[] object;
	public Entity(String[] toStore){
		object = toStore;
	}
	/**
	 * @return the object
	 */
	public String[] getObject() {
		return object;
	}
	/**
	 * @param object the object to set
	 */
	public void setObject(String[] object) {
		this.object = object;
	}
}