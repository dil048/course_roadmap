package course_roadmap;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;
public class classes {
	private ArrayList<String> prerequisites;
	private ArrayList<String> creditNotOffered;
	private String name;
	private String code;
	private String quartersOffered;
	
	public classes(ArrayList<String> prerequisites, String name,String code){
		this.prerequisites = prerequisites;
		this.name = name;
		this.code = code;
		quartersOffered = checkquartersOffered();
	}
	// TODO Check the quarter which the class is offered.
	public String checkquartersOffered(){
		
		return "";
	}
	public ArrayList<String> getPrerequisites() {
		return prerequisites;
	}
	public void setPrerequisites(ArrayList<String> prerequisites) {
		this.prerequisites = prerequisites;
	}
	public ArrayList<String> getCreditNotOffered() {
		return creditNotOffered;
	}
	public void setCreditNotOffered(ArrayList<String> creditNotOffered) {
		this.creditNotOffered = creditNotOffered;
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
