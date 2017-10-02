package course_roadmap;

import java.io.IOException;
import java.util.HashMap;

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PopulateDB_Major {
	private static final String BASEURL = "http://www.ucsd.edu/catalog/";
	private static final String FETCHCOURSENAME = "[class=\"course-name\"]";
	private static HashMap<String, String> majorUrls = new HashMap<>();
	
	public static void main(String args[]) throws IOException
	{
		populatemajorUrls();
		System.out.println(majorUrls.get("Computer Science and Engineering"));
		String major = "Computer Science and Engineering";
		Major m = new Major(major);
		HashSet<String> departments = m.getDepartments();
		for(String s:departments)
		{
			if(s.substring(0, 2).equals("BI"))
			{
				s = "BILD";
			}
			new populatedb(s,majorUrls.get(s));
		}
	}
	public static void populatemajorUrls() throws IOException
	{
		String base = "http://www.ucsd.edu/catalog";
		Document doc = null;
		try {
			doc = Jsoup.connect(base + "/front/courses.html").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements links = doc.select("[href*=../courses]");
		
		for(Element link:links)
		{
			String s = link.attr("href");
			String coursecatologurl = BASEURL+s.substring(3);
			majorUrls.put(getMajor(coursecatologurl).trim(),coursecatologurl);
		}
	}
	public static String getMajor(String s) throws IOException
	{
		Document doc = Jsoup.connect(s).get();
		Elements name = doc.select(FETCHCOURSENAME);
		
		try{
			String major = name.get(0).text();
			String [] parts = major.split(" ");
			return parts[0];
		}catch(Exception e)
		{
			return "";
		}
	}

}
