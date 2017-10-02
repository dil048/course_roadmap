package course_roadmap;
import java.io.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.sql.*;
public class populatedb {
	
	private static final String NBSP = "\u00a0";
	private static final String FETCHCOURSEDESCRIPTION = "[class=\"course-descriptions\"]";
	private static final String FETCHCOURSENAME = "[class=\"course-name\"]";
	private static final String SEPARATOR = ".";
	private static final String TERMCODE = "FA17";
	private static final String URLFORPREREQUISITES ="https://act.ucsd.edu/scheduleOfClasses/"
			+ "scheduleOfClassesPreReq.htm?termCode="+TERMCODE+"&courseId=";
			
	private String major;
	private ArrayList<String> classname;
	private ArrayList<String> preprocessedDesc;
	private String urllink;
	private String quartersOffered;
	private String tableName;
	private Connection conn;
	
	public populatedb(String major, String urllink) throws IOException
	{
		this.tableName = major;
		this.major = major;
		this.urllink = urllink;
		this.classname = new ArrayList<String>();
		this.preprocessedDesc = new ArrayList<String>();
		//Neeed to change this
		this.quartersOffered = "A";
		this.populateDb();
		this.closeConnection();
	}
	public void populateDb() throws IOException
	{
		this.getClassName();
		this.getpreprocessedDescription(this.major);
		this.setup();
		for(int i = 0,j=0;i<this.classname.size()&&
				j<this.preprocessedDesc.size();i++,j++){
			String code = this.getClassCode(this.classname.get(i).replaceAll("\\s", ""));
			//Remove all non-numerical characters
			String str = code.replaceAll("\\D+","");
			if(Integer.parseInt(str)>=200)
			{
				break;
			}
			String name = this.getClassName(this.classname.get(i));
			String desc = this.getClassDescription(this.preprocessedDesc.get(i));
			String pre = this.getClassPrerequisites(code.replaceAll("\\s+", ""));
			this.insertDB(code,name,desc,this.quartersOffered,pre);
		}
	}
	public void setup()
	{
		this.connectToDb();
		this.dropTable();
        this.createTable();
	}
	public void insertDB(String code,String name,String description,
								String quarteroffered,String pre){
        Statement stmt;
        
        try {
            stmt = this.conn.createStatement();
            String statement = "INSERT INTO `course-roadmap`.`"+tableName+"` VALUES (\""+code+"\", \""+name+"\", \""+description+
            		"\", \""+quarteroffered+"\", \""+pre+"\" );";
            //System.out.println(statement);
            stmt.executeUpdate(statement);
            
        }catch(Exception e)
        {
        	System.err.println("Error creating table. Error code below");
        	System.err.println(e.getMessage());
        	System.exit(0);
        }
	}
	
	public void connectToDb()
	{
        try
        {
        	Class.forName ("com.mysql.jdbc.Driver").newInstance ();
        	String userName = "root";
            String password = "password";
            String url = "jdbc:MySQL://localhost/course-roadmap";        
            this.conn = DriverManager.getConnection (url, userName, password);
       
        }catch (Exception ex)
        {
		       System.err.println ("Cannot connect to database server");
			   ex.printStackTrace();
        } 
	}
	
	public void dropTable()
	{
		Statement stmt;
        try {
            stmt = conn.createStatement();
            String statement = "Drop TABLE `course-roadmap`.`"+this.tableName+"`";
            stmt.executeUpdate(statement);
            
        }catch(com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e)
        {
        	// First time. Therefore don't worry about the error.
        }
        catch(Exception e)
        {
        	System.err.println("Error dropping table. Error code below");
        	System.err.println(e.toString());
        }
	}
	public void createTable()
	{
		Statement stmt;
        try {
            stmt = conn.createStatement();
            String statement = "CREATE TABLE `course-roadmap`.`"+this.tableName+"` (code VARCHAR(45), "
            		+ "name LONGTEXT,description LONGTEXT, quarterOffered VARCHAR(45), prerequistites LONGTEXT);";
            //System.out.println(statement);
            stmt.executeUpdate(statement);
            
        }catch(Exception e)
        {
        	System.err.println("Error creating table. Error code below");
        	System.err.println(e.getMessage());
        }
	}
	public void getClassName() throws IOException
	{
		Document doc = Jsoup.connect(this.urllink).get();
		Elements name = doc.select(FETCHCOURSENAME);
		
		for(int i=0;i<name.size();)
		{
			String n = name.get(i++).text();
			while(n.equals(NBSP) || n.length()==0)
			{
				n = name.get(i++).text();
			}
			//System.out.println(n);
			classname.add(n);
		}
	}
	
	public void getpreprocessedDescription(String majorname) throws IOException
	{
		Document doc = Jsoup.connect(this.urllink).get();
		Elements description = doc.select(FETCHCOURSEDESCRIPTION);
		
		for(int i=0;i<description.size();)
		{
			String n = description.get(i++).text();
			while(n.equals(NBSP) || n.length()==0)
			{
				n = description.get(i++).text();
			}
			if(i==description.size()-1)
			{
				break;
			}
			preprocessedDesc.add(n);
		}
	}
	
	public String getClassCode(String classname)
	{
		// Class comes in the format of CSE XXX. class name. locate the dot
		int dotlocation = classname.indexOf(SEPARATOR);
		return classname.substring(0,dotlocation);
	}
	
	public String getClassName(String classname)
	{
		int dotlocation = classname.indexOf(SEPARATOR);
		return classname.substring(dotlocation+2);
	}
	
	public String getClassDescription(String description)
	{
		if(description.indexOf("Prerequisites")!=-1){
			description= description.substring(0, description.indexOf("Prerequisites"));
		}
		return description;
	}
	public String getClassPrerequisites(String classname)
	{
		String urlToFetch = URLFORPREREQUISITES+classname;
		String prerequisites = "None";
		try {
			Document doc = Jsoup.connect(urlToFetch).get();
			Elements lcel = doc.getElementsByTag("table");
			
			String html  = lcel.text();
			String[] parts = html.split("\\s+(?=[0-9])");
			StringBuilder result = new StringBuilder();
			for(int i =1 ;i<parts.length;i++)
				result.append("( "+this.removeClassName(parts[i].substring(3))+" ) and ");
			if(result.length()!=0)
				prerequisites = result.substring(0, result.length()-4);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prerequisites;
	}
	
	public String removeClassName(String entireName)
	{
		entireName = entireName.replaceAll("\\(.*?\\)", "");
		entireName = entireName.replaceAll("\\)", "");
		entireName = entireName.replaceAll("\\s{2,}", " ").trim();
		//System.out.println(entireName);
		return entireName;
	}
	public void closeConnection()
	{
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//public static ArrayList<url> getlinks
	
}
