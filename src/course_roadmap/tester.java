package course_roadmap;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.regex.*;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.sql.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.List;

public class tester {
	public static void main(String args []) throws IOException
	{
		
		//new populatedb("MATH","http://www.ucsd.edu/catalog/courses/MATH.html");
		/*Classes c = new Classes("CSE101");
		ArrayList<String> a = c.getPreq();
		for(String s:a)
		{
			System.out.println(s);
		}
		String key = "CSE100";
		/*for(String s:a)
		{
			s= s.trim();
			s = s.substring(1,s.length()-1).trim();
			String [] parts = s.split("or");
			for(String str: parts)
			{
				System.out.println(str.trim());
			}
		}*/
		User u = new User("Computer Science and Engineering");
		String response = "";
		do
		{
			System.out.println("Enter a to add class, p to print schedule, q to quit");
			Scanner sc = new Scanner(System.in);
			response = sc.next();
			if(response.toLowerCase().equals("a"))
			{
				System.out.println("What classes?");
				Classes c = new Classes(sc.next());
				System.out.println("What quarter?");
				u.addClasses(Integer.parseInt(sc.next()), c, false);
			}else if(response.toLowerCase().equals("p"))
			{
				u.printClass();
			}
			
		}while(!response.equals("q"));
		
		System.out.println("Bye!");
		/*Classes a = new Classes("MATH20A");
		Classes b = new Classes("MATH20B");
		Classes c = new Classes("MATH20C");
		Classes d = new Classes("MATH109");
		Classes e = new Classes("MATH20D");
		Classes f = new Classes("MATH20E");
		Classes g = new Classes("MATH170A");
		Classes h = new Classes("MATH18");*/
		
		
		/*
		 * 0-Before School Start
		 * 1-Fresh Fall
		 * 2-Fresh Winter
		 * 3-Fresh Spring
		 * 4-Fresh Summer session 1
		 * 5-Fresh Summer session 2
		 * 6-Soph Fall
		 * 7-Soph Winter
		 * ...
		 */
		//Classes x = new Classes("CSE12");
		
		//u.addClasses(3, a, true);
		//quarter [] q = u.getSchedule();
		/*for(quarter p : q)
		{
			System.out.println(p.getQuarter());
			for(Classes m : p.getClassList())
			{
				System.out.println(m.getClassCode());
			}
		}
		//System.out.println("The size is " + u.getSchedule()[1].getClassList().size());
		//for(Classes i : u.getSchedule()[1].getClassList())
		//	System.out.println("The code is "+i.getCode());
		
		//System.out.println("The size is " + u.getSchedule()[1].getClassList().size());
		//for(Classes i : u.getSchedule()[1].getClassList())
		//	System.out.println("The code is "+i.getCode());
		//System.out.println(u.canAdd(3, x));
		//new Major("CS26 - Computer Science(old)");
		//new Controller("CSE");
		//new populatedb("CSE","http://www.ucsd.edu/catalog/courses/CSE.html");
		//This is for fetching all the courses link
		
		/*String base = "http://www.ucsd.edu/catalog";
		Document doc = Jsoup.connect(base + "/front/courses.html").get();
		Elements links = doc.select("[href*=../courses]");
		
		System.out.println("number of majors"+links.size());
		for(Element link:links)
		{
			String s = link.attr("href");
			System.out.println(s);
		}
		*/
		
		/*Document doc = Jsoup.connect("https://act.ucsd.edu/scheduleOfClasses/"
				+ "scheduleOfClassesPreReq.htm?termCode=FA17&courseId=CSE8B").get();
		Elements lcel = doc.getElementsByTag("table");
		String finalresult = "None";
		String html  = lcel.text();
		System.out.println(html);
		String[] parts = html.split("\\s+(?=[0-9])");
		StringBuilder result = new StringBuilder();
		System.out.println();
		for(int i =1 ;i<parts.length;i++)
			result.append("["+parts[i].substring(3)+"] and ");
		if(result.length()!=0)
			 finalresult = result.substring(0, result.length()-4);
		System.out.println(finalresult);
	*/
		//Elements name = doc.select("[span]");
		
		//System.out.println(name.size());
		//for(Element link:name){
		//	System.out.println(name.text());
		//}
		
		
		/*Elements description = doc.select("[class=\"course-descriptions\"]");
		
		for(int i=0,j = 0;i<name.size() && j<description.size();)
		{
			String n = name.get(i++).text();
			while(n.equals("\u00a0")||n.length()==0)
			{
				n = name.get(i++).text();
			}
			int dotlocation = n.indexOf(".");
			String firstpart = n.substring(0,dotlocation);
			String secondpart = n.substring(dotlocation+2);
			String d = description.get(j++).text();
			while(d.equals("\u00a0")||d.length()==0)
			{
				d = description.get(j++).text();
			}
			String descrition;
			String preq;
			if(d.indexOf("Prerequisites")!=-1){
				descrition= d.substring(0, d.indexOf("Prerequisites"));
				preq = d.substring(d.indexOf("Prerequisites"));
			}else
			{
				descrition = d;
				preq = "None";
			}
			System.out.printf("%s\n%s\n%s\n%s\n\n",firstpart,secondpart,descrition,preq);
		}*/
		/*
		System.out.println("\n\n***** MySQL JDBC Connection Testing *****");
		Connection conn = null;
        try
        {
        	Class.forName ("com.mysql.jdbc.Driver").newInstance ();
        	String userName = "root";
            String password = "password";
            String url = "jdbc:MySQL://localhost/new_schema";        
            conn = DriverManager.getConnection (url, userName, password);
            System.out.println ("\nDatabase Connection Established...");
       
        }
       catch (Exception ex)
        {
		       System.err.println ("Cannot connect to database server");
			   ex.printStackTrace();
        } 
        Statement stmt = null;
        ResultSet rs = null;
        System.out.println ("\n-------------SQL DATA-------------\n");
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM class");
            int count = rs.getMetaData().getColumnCount();
            System.out.println(count);
            while(rs.next())
            {
            	StringBuilder s = new StringBuilder();
            	for(int i =1;i<=count;i++)
            	{
            		s.append(rs.getString(i)+",");
            	}
            	String f = s.substring(0, s.length()-1);
            	System.out.println(f);
            	
            }
	}catch (Exception ex){
		System.err.println ("Cannot get columns");
	   ex.printStackTrace();
	}*/
	    }
	//Use to check if preq has been satisfy
	private static boolean isContain(String source, String subItem){
        String pattern = "\\b"+subItem+"\\b";
        Pattern p=Pattern.compile(pattern);
        Matcher m=p.matcher(source);
        return m.find();
   }
	
}
