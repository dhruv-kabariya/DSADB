
import java.io.IOException;
import java.io.RandomAccessFile;

public class Birds extends DbObject {
	
	private String name;
	protected int nameLen = 30;

	private String color;
	protected int colorLen = 30;
	
	private String habitat;
	protected int habitatLen=30;
	
	private int heightLatitude;
	protected int heightLatitudeLen = 4;

	private String sound;
	protected int soundLen = 50;

	private int population; 
	protected int populationLen = 4;


	protected int size = 2*nameLen+2*colorLen + 2*habitatLen + 2*soundLen   + heightLatitudeLen+populationLen;
	//the length of string is counted twice because of writing into file as array
	
	public Birds() {	
	}

	public Birds(String s1, String s2, String s3, String s4,int i,int p) {
		name=s1;
		color=s2;
		habitat=s3;
		sound=s4;
		population = p ;
		heightLatitude = i;
	}
	
	public boolean equals(Object d) {
		
		return name.trim().equals(((Birds) d).name.trim());
	}
	@Override
	public void writeToFile(RandomAccessFile out) throws IOException {
		writeString(name, out);
		writeString(color, out);
		out.writeInt(population);
		out.writeInt(heightLatitude);
		writeString(habitat,out);
		writeString(sound,out);
		
	}

	@Override
	public void readFromFile(RandomAccessFile in) throws IOException {
		name = readString(nameLen, in);
		color = readString(colorLen, in);
		population = in.readInt();
		heightLatitude = in.readInt();
		habitat=readString(habitatLen, in);
		sound = readString(soundLen, in);

	}

	@Override
	public void readFromConsole() {
		kb.useDelimiter("\r\n");
		
		System.out.print("Enter name: ");
		name=kb.next();
		
		for(int i = name.length(); i< nameLen; i++)
			name+=' ';

		System.out.print("Color : ");
		color= kb.next();
		for(int i = color.length(); i< colorLen; i++)
			color+=' ';
		
		System.out.print("Population : ");
		population = kb.nextInt();
		
		//System.out.print("Here is error: " + kb.next());
		
		System.out.print("Bird flying Latitude: ");
		heightLatitude=kb.nextInt();

		System.out.print("Habitat : ");
		habitat=kb.next();


		for(int i = habitat.length(); i< habitatLen; i++)
			habitat+=' ';
		
		System.out.print("Sound : ");
		sound=kb.next();

		for(int i = sound.length(); i< soundLen; i++)
			sound+=' ';
		
		
		}
		
	

	@Override
	public void writeLegibly() {
		System.out.print("The Bird name is " + name.trim() + ".\nBird color is " + color.trim() + ".\nIt can Fly Upto " + heightLatitude + " meters.\nIt lives on " + habitat.trim() + ".\nIt sounds like " + sound.trim() + ".\nTheir population is "+ population + " .\n");
	}

	@Override
	public void readKey() {
		System.out.println("Enter name: ");
		kb.useDelimiter("\r\n");
		name= kb.next();
System.out.println("Comparing name: " + name);
	}

	@Override
	public void copy(DbObject[] db) {
		db[0] = new Birds(name, color, habitat, sound,heightLatitude,population);

	}

	@Override
	public int size() {
		
		return size;
	}

}