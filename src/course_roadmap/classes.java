package course_roadmap;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;
public class classes {
	private String prerequisites;
	private String name;
	private String code;
	private String quartersOffered;
	
	public classes(String prerequisites, String name,String code){
		this.prerequisites = prerequisites;
		this.name = name;
		this.code = code;
		quartersOffered = checkquartersOffered();
	}
	// TODO Check the quarter which the class is offered.
	public String checkquartersOffered(){
		
		return "";
	}
	public String getPrerequisites() {
		return prerequisites;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	

}
