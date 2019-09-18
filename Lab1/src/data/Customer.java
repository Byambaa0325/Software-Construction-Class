package data;

public class Customer {
	private int id;
	private String name;

	public Customer(String name2) {
		this.id = 0;
		this.name = name2;
	}
	public Customer(int id, String name2) {
		this.id = id;
		this.name = name2;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


}
