package course_roadmap;

import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PopulateDB_Major {
	private static final String BASEURL = "http://www.ucsd.edu/catalog/";
	private static final String FETCHCOURSENAME = "[class=\"course-name\"]";
	private static final String FETCCHMAJORNAME = "td";
	private static final String getMajorUrl = "http://blink.ucsd.edu/instructors/academic-info/majors/major-codes.html";
	
	// Aberration -> websiteLink
	private static HashMap<String, String> abberMajorUrls = new HashMap<>();
	// full name -> Aberration 
	private static HashMap<String,String> majorToAberration = new HashMap<>();
	// Aberration -> full name
	private static HashMap<String,String> aberrationTomajor = new HashMap<>();
	// full name -> websiteLink
	private static HashMap<String, String> fullMajorUrls = new HashMap<>();
 	
	public static void main(String args[]) throws IOException
	{
		populatemajorUrls();
		for(String s: majorToAberration.keySet())
		{
			if(!s.contains("College"))
				fullMajorUrls.put(s, abberMajorUrls.get(majorToAberration.get(s)));
		}
		
		for(String s:abberMajorUrls.keySet())
		{
			System.out.println(s+":" + abberMajorUrls.get(s));
			try{
				if(s.substring(0, 2).equals("BI"))
				{
					s = "BILD";
				}
				new PopulateDepartmentClasses(s,abberMajorUrls.get(s));
			}catch(Exception e)
			{
				//e.printStackTrace();
			}
		}
		
		/*System.out.println(majorUrls.keySet().size());
		for(String s : majorUrls.keySet())
		{
			System.out.println(s);
		}*/
		/*System.out.println(majorUrls.get("Computer Science and Engineering"));
		String major = "Computer Science and Engineering";
		Major m = new Major(major);
		HashSet<String> departments = m.getDepartments();
		for(String s:departments)
		{
			if(s.substring(0, 2).equals("BI"))
			{
				s = "BILD";
			}
			new PopulateDepartmentClasses(s,majorUrls.get(s));
		}*/
	}
	
	public static void populatemajorUrls()
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
			abberMajorUrls.put(getMajor(coursecatologurl).trim(),coursecatologurl);
		}
	}
	
	public static String getMajor(String s)
	{
		Document doc = null;
		try {
			doc = Jsoup.connect(s).get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Elements name = doc.select(FETCHCOURSENAME);
		String title = doc.title().trim();
		int indexToEnd = title.indexOf("Courses");
		if(indexToEnd>0)
			title = title.substring(0,indexToEnd).trim();
		try{
			String major = name.get(0).text();
			String [] parts = major.split(" ");
			int code = Integer.parseInt(major.replaceAll("\\D+",""));
			if(code<200)
			{
				majorToAberration.put(title,parts[0]);
				aberrationTomajor.put(parts[0], title);
				return parts[0];
			}else
			{
				return "";
			}
		}catch(Exception e)
		{
			return "";
		}
	}

}
