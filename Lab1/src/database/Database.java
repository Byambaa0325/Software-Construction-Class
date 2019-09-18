package database;

public interface Database {
	public void insert(Entity a);
	public int search(Entity a);
	public void update(Entity a, Entity b);
	public void delete(int id);
	public Entity[] getData();
}
