package database;
/*
* Interface for Database
* */
public interface Database {
	
	void insert(Entity a);

	int search(Entity a);

	void update(Entity a, Entity b);

	void delete(int id);

	Entity[] getData();
}
