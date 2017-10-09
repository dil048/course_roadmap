package course_roadmap;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class quarter {
	int totalUnits;
	ArrayList<Classes> classlist;
	int quarter;
	
	public quarter(int quarter)
	{
		this.quarter = quarter;
		classlist = new ArrayList<>();
	}
	public String getQuarter()
	{
		switch(this.quarter % 5){
			case -1:
				return "BEFORE SCHOOL START";
			case 0:
				return "FALL";
			case 1:
				return "WINTER";
			case 2:
				return "SPRING";
			case 3:
				return "SUMMER SESSION 1";
			case 4:
				return "SUMMER SESSION 2";
			default:
				//Need to code this later.
				return "ERROR";
		}
		
	}
	/*
	 * 0-Before School Start
	 * 0-Fresh Fall
	 * 1-Fresh Winter
	 * 2-Fresh Spring
	 * 3-Fresh Summer session 1
	 * 4-Fresh Summer session 2
	 * 5-Soph Fall
	 * 6-Soph Winter
	 * ...
	 */
	public void addClasses(Classes c)
	{
		this.classlist.add(c);
	}
	
	public int getUnits()
	{
		this.totalUnits = 0;
		for(Classes c : classlist)
		{
			totalUnits+=c.getUnits();
		}
		return this.totalUnits;
	}
	
	public ArrayList<Classes> getClassList()
	{
		return this.classlist;
	}
	
	public boolean contains(String s)
	{
		for(Classes c : this.classlist)
		{
			if(c.getClassCode().equals(s))
			{
				return true;
			}
		}
		return false;
	}

}
