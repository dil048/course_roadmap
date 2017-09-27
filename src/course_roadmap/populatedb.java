package course_roadmap;
import java.net.*;
import java.io.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.sql.*;
public class populatedb {
	
	private static final String NBSP = "\u00a0";
	private static final String FETCHCOURSEDESCRIPTION = "[class=\"course-description\"]";
	private static final String FETCHCOURSENAME = "[class=\"course-name\"]";
	private static final String SEPARATOR = ".";
	
	private String major;
	private ArrayList<String> classname;
	private ArrayList<String> preprocessedDesc;
	private ArrayList<classes> db;
	private String urllink;
	private String quartersOffered;
	
	public populatedb(String major, String urllink)
	{
		this.major = major;
		this.urllink = urllink;
		this.classname = new ArrayList<String>();
		this.preprocessedDesc = new ArrayList<String>();
		//Neeed to change this
		this.quartersOffered = "A";
	}
	public void populateClassname()
	{
		
	}
	public void getClassName() throws IOException
	{
		Document doc = Jsoup.connect(this.urllink).get();
		Elements name = doc.select(FETCHCOURSENAME);
		
		for(int i=0;i<name.size();)
		{
			String n = name.get(i++).text();
			while(n.equals(NBSP) || n.length()==0)
			{
				n = name.get(i++).text();
			}
			classname.add(n);
		}
	}
	
	public void getpreprocessedDescription(String majorname) throws IOException
	{
		Document doc = Jsoup.connect(this.urllink).get();
		Elements description = doc.select(FETCHCOURSEDESCRIPTION);
		
		for(int i=0,j = 0;i<description.size();)
		{
			String n = description.get(i++).text();
			while(n.equals(NBSP) || n.length()==0)
			{
				n = description.get(i++).text();
			}
			preprocessedDesc.add(n);
		}
	}
	
	public void createClass()
	{
		return;
	}
	
	public String getClassCode(String classname)
	{
		// Class comes in the format of CSE XXX. class name. locate the dot
		int dotlocation = classname.indexOf(SEPARATOR);
		return classname.substring(0,dotlocation);
	}
	
	public String getClassName(String classname)
	{
		int dotlocation = classname.indexOf(SEPARATOR);
		return classname.substring(dotlocation+2);
	}
	
	
	
	//public static ArrayList<url> getlinks
	
}
