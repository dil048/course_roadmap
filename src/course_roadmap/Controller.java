package course_roadmap;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Controller {
	private User user;
	private static final String BASEURL = "http://www.ucsd.edu/catalog/";
	private HashMap<String, String> majorUrls;
	
	public void populatemajorUrls()
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
			System.out.println(coursecatologurl);
			majorUrls.put(this.getMajor(coursecatologurl),coursecatologurl);
		}
	}
	
	public String getMajor(String s)
	{
		Document doc = null;
		try {
			doc = Jsoup.connect(s).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String title = doc.title();
		int indexofCourses = title.indexOf("Courses");
		if(indexofCourses!=-1)
			title = title.substring(0, indexofCourses);
		int indexofLeftBracket = title.indexOf("(");
		if(indexofLeftBracket!=-1)
			title = title.substring(0, indexofLeftBracket);
		return title;
	}

}
