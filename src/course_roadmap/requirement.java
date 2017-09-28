package course_roadmap;
import java.util.*;
public class requirement {
	private String sectionName;
	private ArrayList<Classes> classesNeeded;
	private int number;
	
	public requirement(int amount,String sectionName,ArrayList<Classes> classesNeeded)
	{
		this.sectionName = sectionName;
		this.classesNeeded = classesNeeded;
		this.number = amount;
	}
	public String getSectionName()
	{
		return this.sectionName;
	}
	public ArrayList<Classes> getRequirement()
	{
		return this.classesNeeded;
	}

}
