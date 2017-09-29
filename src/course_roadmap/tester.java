package course_roadmap;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.List;

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
		new Major("CS26 - Computer Science(old)");
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
	
}
