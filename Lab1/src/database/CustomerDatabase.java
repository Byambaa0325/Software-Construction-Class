package database;

import java.util.ArrayList;

import data.Customer;
import database.Database;
import database.Entity;

/**
 * Database Implementation for Customer object
 */
public class CustomerDatabase implements Database{

	/**
	 * Arraylist to store entities
	 */
	private ArrayList<Entity> db;

	/**
	 * Default constructor
	 */
	public CustomerDatabase(){
		db = new ArrayList<>();
	}

	/**
	 * Insert Entity to database
	 * @param toInsert The object to be inserted
	 */
	public void insert(Entity toInsert) {
		Customer customer = entityToCustomer(toInsert);
		String[] args = customerToStringArray(customer);
		//Add to database
		db.add(new Entity(args));		
	}

	/**
	 * Remove entity by id
	 * @param id The id in database
	 */
	public void delete(int id) {
		db.remove(id-1);
	}

	/**
	 * Linear Search for Entity from database
	 * @param toSearch Entity to be searched
	 * @return the index in the database
	 */
	public int search(Entity toSearch) {
		int index = 0;
		for(Entity entity : db) {
			if(equals(entity,toSearch)) {
				return index+1;
			}
			index++;
		}
		return -1;
	}

	/**
	 * Update existing Entity with new Entity
	 * @param old The old entity in the database
	 * @param update the entity to replace the old entity
	 */
	public void update(Entity old, Entity update) {
		int index = search(old)-1;
		db.remove(index);
		db.add(index, update);
	}

	/**
	 * Return data stored as Entity array
	 * @return array of entities
	 */
	public Entity[] getData() {
		Entity[] toReturn = new Entity[db.size()];
		for(int i = 0 ; i< toReturn.length; i++) {
			toReturn[i] = db.get(i);
		}
		return toReturn;
	}

	/**
	 * Private method to compare entities for this database
	 * @param A Entity A
	 * @param B Entity B
	 * @return boolean to denoting equality
	 */
	private boolean equals (Entity A, Entity B) {
		if(A.getObject().length == B.getObject().length) {
			for(int i = 0 ; i <A.getObject().length; i++) {
				if(!A.getObject()[i].equals(B.getObject()[i])) {
					return false;
				}
			}
		}
		else {
			return false;
		}
		return true;
	}

	/**
	 * Construct customer from entity
	 * @param toInsert the entity to be converted
	 * @return the entity as customer
	 */
	private Customer entityToCustomer(Entity toInsert) {
		Customer customer;//Single argument contructor
		if(toInsert.getObject().length==1) {
			customer=new Customer(toInsert.getObject()[0]);
		}
		//Double argument constructor
		else {
			customer=new Customer(Integer.parseInt(toInsert.getObject()[0]), toInsert.getObject()[1]);
		}
		return customer;
	}

	/**
	 * Customer as string array
	 * @param customer customer to be converted
	 * @return string array of attributes
	 */
	private String[] customerToStringArray(Customer customer) {
		return new String[]{
				""+customer.getId(),
				customer.getName()
		};
	}

}
