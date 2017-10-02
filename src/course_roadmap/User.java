package course_roadmap;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class User {
	//This maps all the majors with their urls.
	private quarter[] schdule;
	public User(String major)
	{
		Major m = new Major(major);
		/*
		 * -1-Before School Start
		 * 0-Fresh Fall
		 * 1-Fresh Winter
		 * 2-Fresh Spring
		 * 3-Fresh Summer session 1
		 * 4-Fresh Summer session 2
		 * 5-Soph Fall
		 * 6-Soph Winter
		 * ...
		 */
		schdule = new quarter[26];
		for(int i = 0;i<this.schdule.length;i++)
		{
			schdule[i] = new quarter(i-1);
		}
		//Minor minor = new Minor();
	}
	/*
	 * 0-Before School Start
	 * 0-Fresh Fall
	 * 1-Fresh Winter
	 * 2-Fresh Spring
	 * 3-Fresh Summer session 1
	 * 4-Fresh Summer session 2
	 * 5-Soph Fall
	 * 6-Soph Winter
	 * ...
	 */
	public boolean addClasses(int quarter ,Classes c,boolean override)
	{
		if(override || this.canAdd(quarter,c) == 0)
		{
			this.schdule[quarter].addClasses(c);
			return true;
		}
		return false;
	}
	// return 1 if already taken. Can override
	// return 2 if prerequisites are not satisfied. Can override
	// return 0 for success
	public int canAdd(int quarter , Classes c)
	{
		if(quarter ==0)
		{
			return 0;
		}
		System.out.println("Checking to see if we can add " + c.getClassCode());
		for(int i =0;i<quarter;i++)
		{
			if(this.schdule[i].contains(c.getClassCode()))
			{
				System.out.println(c.getClassCode()+" already taken");
				return 1;
			}
		}
		ArrayList<String> preq = c.getPreq();
		int count = 0;
		if(preq.get(0).equals("None"))
		{
			System.out.println(c.getClassCode()+" has no preq");
			return 0;
		}
		for(String s:preq)
		{
			s= s.trim();
			s =s.substring(1,s.length()-1).trim();
			String [] parts = s.split("or");
			boolean satisfied = false;
			System.out.println("Entire section of preq string "+ s);
			for(String str: parts)
			{
				for(int i =0;i<quarter;i++)
				{
					System.out.println("The class I am checking is "+ str);
					if(this.schdule[i].contains(str.trim()))
					{
						satisfied=true;
					}
				}
				if(satisfied)
				{
					count ++;
				}
				satisfied = false;
			}
		}
		System.out.println("count is"+count);
		System.out.println("the preq size is "+preq.size());
		if(count == preq.size())
		{
			return 0;
		}else
		{
			System.out.println("lalala");
			return 2;
		}
	}
	public void printClass()
	{
		System.out.println("------------The following classes are in the list -----------");
		for(quarter q: this.schdule)
		{
			System.out.println("-----------------Quarter: "+q.getQuarter()+"---------------");
			for(Classes c : q.getClassList())
				System.out.println(c.getClassCode());
		}
	}
	/*public int[] sectionsSatisfied()
	{
		
	}*/
	
	public quarter[] getSchedule()
	{
		return this.schdule;
	}
	private static boolean isContain(String source, String subItem){
        String pattern = "\\b"+subItem+"\\b";
        Pattern p=Pattern.compile(pattern);
        Matcher m=p.matcher(source);
        return m.find();
   }
	
	
	
	

}
