package database.WritingStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import database.Database;
import database.Entity;

public class TextFileStrategy implements DatabaseSavingStrategy{

	public TextFileStrategy() {
		
	}
	@Override
	public void save(String filename, Database db) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filename+".txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		Entity[] data = db.getData();
		int indexCounter = 0;
		for(Entity entity : data) {
			indexCounter+=1;
			String toSave = indexCounter+"-"+String.join(",", entity.getObject());
			writer.println(toSave);
		}	
		writer.close();
	}

	@Override
	public Entity[] read(String filename, Database db) {
		// TODO Auto-generated method stub
		ArrayList<Entity> lines = new ArrayList<>();
		Scanner sc =null;
		try {
			sc = new Scanner(new File(filename+".txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("FileNotFound");
			return null;
		}
		while(sc.hasNextLine()) {
			
			String line = sc.nextLine();
			String parts[] = line.split("-");
			line = parts[1];
			lines.add(new Entity(line.split(",")));
		}		
		Entity[] toReturn = new Entity[lines.size()];
		for(int i = 0 ; i< toReturn.length; i++) {
			toReturn[i] = lines.get(i);
		}
		sc.close();
		return toReturn;
	}

}
