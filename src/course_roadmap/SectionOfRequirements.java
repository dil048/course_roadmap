package course_roadmap;
import java.util.*;
public class SectionOfRequirements {
	private String[] classesNeeded;
	private int numberOfClassesNeed;
	
	public SectionOfRequirements(int amount, String[] classesNeeded)
	{
		this.classesNeeded = classesNeeded;
		if(amount == 0)
		{
			this.numberOfClassesNeed = classesNeeded.length;
		}else
		{
			this.numberOfClassesNeed = amount;
		}
	}
	public String[] getRequirement()
	{
		return this.classesNeeded;
	}
	public int getNumber()
	{
		return this.numberOfClassesNeed;
	}

}
