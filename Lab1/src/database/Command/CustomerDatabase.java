package database.Command;

import java.util.ArrayList;

import data.Customer;
import database.Database;
import database.Entity;

public class CustomerDatabase implements Database{
	private ArrayList<Entity> db;
	public CustomerDatabase(){
		db = new ArrayList<>();
	}

	public void insert(Entity toInsert) {
		// TODO Auto-generated method stub
		Customer customer = null;
		if(toInsert.getObject().length==1) {
			customer=new Customer(toInsert.getObject()[0]); 
		}
		else {
			customer=new Customer(Integer.parseInt(toInsert.getObject()[0]), toInsert.getObject()[1]);
		}
		String args[] =  {
			""+customer.getId(),
			customer.getName() 
			};
		
		db.add(new Entity(args));		
	}
	
	public void delete(int id) {
		db.remove(id-1);
	}
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
	public void update(Entity old, Entity update) {
		int index = search(old)-1;
		db.remove(index);
		db.add(index, update);
	}
	public Entity[] getData() {
		Entity[] toReturn = new Entity[db.size()];
		for(int i = 0 ; i< toReturn.length; i++) {
			toReturn[i] = db.get(i);
		}
		return toReturn;
	}
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

}
