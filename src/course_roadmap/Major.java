package course_roadmap;
import java.net.*;
import java.io.*;
import java.util.*;

public class Major {
	private static final String basePath = "majorRequirements/";
	
	private ArrayList<SectionOfRequirements> listofClasses;
	private HashSet<String> departmentInvolved;
	//Maps major name to their String url
	private String path = "majorRequirements/";
	
	public Major(String majorname)
	{
		// Need to change, will fetch from database instead.
		this.path =basePath+majorname+".txt";
		listofClasses = new ArrayList<>();
		departmentInvolved = new HashSet<>();
		this.populatelist();
	}
	private void populatelist()
	{
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
	
	private void addTolistofClasses(String s)
	{
		s = s.substring(1, s.length()-1);
		String [] parts = s.split(";");
		int number = Integer.parseInt(parts[0]);
		String [] classesNeeded = parts[1].split(",");
		this.listofClasses.add(new SectionOfRequirements(number,classesNeeded));
		String [] departments = parts[1].replaceAll(","," ").split(" ");
		for(String str : departments)
		{
			if(allLetters(str))
			{
				this.departmentInvolved.add(str);
			}
		}
		
	}
	private boolean allLetters(String s)
	{
		for(char c : s.toCharArray())
		{
			if(c<'A'||c>'Z')
			{
				return false;
			}
		}
		return true;
	}
	public ArrayList<SectionOfRequirements> getRequirements()
	{
		return this.listofClasses;
	}
}
