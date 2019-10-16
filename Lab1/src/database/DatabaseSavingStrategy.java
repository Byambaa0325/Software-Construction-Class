package database;

public interface DatabaseSavingStrategy {
	public void save(String filename, Database db);
	public Entity[] read(String filename, Database db);
}
