package course_roadmap;
import java.net.*;
import java.io.*;
import java.util.*;

public class Major {
	private static final String basePath = "majorRequirements/";
	
	private ArrayList<SectionOfRequirements> listofClasses;
	//Maps major name to their String url
	private String majorname;
	private String path = "majorRequirements/";
	
	public Major(String majorname)
	{
		this.majorname = majorname;
		this.path =basePath+majorname+".txt";
		listofClasses = new ArrayList<>();
		this.populatelist();
	}
	public void populatelist()
	{
		System.out.println(this.path);
		File f = new File(this.path);
		Scanner sc;
		try {
			sc = new Scanner(f);
			while(sc.hasNextLine())
			{
				String s =sc.nextLine();
				this.addTolistofClasses(s);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(f.listFiles());
		
	}
	public void addTolistofClasses(String s)
	{
		s = s.substring(1, s.length()-1);
		String [] parts = s.split(";");
		int number = Integer.parseInt(parts[0]);
		parts = parts[1].split(",");
		this.listofClasses.add(new SectionOfRequirements(number,parts));
	}
	public ArrayList<SectionOfRequirements> getRequirements()
	{
		return this.listofClasses;
	}
}
