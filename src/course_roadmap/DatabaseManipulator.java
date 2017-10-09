package course_roadmap;
import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class DatabaseManipulator {
	private static final String BASEURL = "http://www.ucsd.edu/catalog/";
	private static final String FETCHCOURSENAME = "[class=\"course-name\"]";
	private static HashMap<String, String> majorUrls = new HashMap<>();
	public static void main(String []args)
	{
		populatemajorUrls();
		for(String s:majorUrls.keySet())
		{
			System.out.println(s);
			if(s.substring(0, 2).equals("BI"))
			{
				s = "BILD";
			}
			new PopulateDepartmentClasses(s,majorUrls.get(s));
		}
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
			majorUrls.put(getMajor(coursecatologurl).trim(),coursecatologurl);
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
		String major = "";
		try{
			major = name.get(0).text();
			String [] parts = major.split(" ");
			return parts[0];
		}catch(Exception e)
		{
			return "";
		}
	}
}
