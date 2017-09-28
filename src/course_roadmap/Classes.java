package course_roadmap;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;

//Classes that a major will require to complete.
public class Classes {
	
	private ArrayList<String> prerequisites;
	private String name;
	private String code;
	private String quartersOffered;
	
	public Classes(ArrayList<String> prerequisites, 
			String name,String code){
		this.prerequisites = prerequisites;
		this.name = name;
		this.code = code;
		quartersOffered = checkquartersOffered();
	}
	// TODO Check the quarter which the class is offered.
	public String checkquartersOffered(){
		return "A";
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
