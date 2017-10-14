package course_roadmap;

import java.util.*;
import java.sql.*;

//Classes that a major will require to complete.
public class Classes {
	
	private String classCode;
	private String department;
	private ArrayList<String> preq;
	private Connection conn;
	private int units;
	//Later feature for calculting the overall average.
	//private String grade;
	public Classes(String code)
	{
		this.classCode = code;
		this.connectToDb();
		this.setDepartment();
		this.populateFields();
		this.closeConn();
	}
	public void closeConn()
	{
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void connectToDb()
	{
        try
        {
        	Class.forName ("com.mysql.jdbc.Driver").newInstance ();
        	String userName = "root";
            String password = "password";
            String url = "jdbc:MySQL://127.0.0.1:3306/course-roadmap";        
            this.conn = DriverManager.getConnection (url, userName, password);
       
        }catch (Exception ex)
        {
		       System.err.println ("Cannot connect to database server");
			   ex.printStackTrace();
        } 
	}
	
	public void populateFields()
	{
		this.preq = new ArrayList<String>();
		Statement stmt;
		String preqs = "";
		try {
			stmt = this.conn.createStatement();
			String queryStatement = "SELECT * FROM "+ this.department+" WHERE code = '"
											+this.classCode+"';";
			ResultSet rs = stmt.executeQuery(queryStatement);
			if(rs.next())
			{
				preqs = rs.getString("prerequistites");
				String name = rs.getString("name");
				int indexOfleftbracket = name.indexOf("(");
				String unit = name.substring(indexOfleftbracket);
				this.units = Integer.parseInt(unit.replaceAll("\\D+",""));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String [] parts = preqs.split("and");
		for(String s : parts)
		{
			this.preq.add(s);
		}
	}
	
	@Override
	public boolean equals(Object o)
	{
		// check for null condition and if o is a Point object
	    if (o==null || !(o instanceof Classes)) 
	    {
	       return false;
	    }
	    else 
	    {
	       // check if the x coordinate and y coordinate matches
	       if (((Classes) o).getClassCode().equals(this.getClassCode()))
	       {
	          return true;
	       }
	    }
	    return false;
	}
	public void setDepartment()
	{
		int count = 0;
		for(int i = 0;i<this.classCode.length();i++)
		{
			if(this.classCode.charAt(i)<'A'||this.classCode.charAt(i)>'Z')
			{
				break;
			}
			count++;
		}
		this.department = this.classCode.substring(0, count);
	}
	public String getDepartment()
	{
		return this.department;
	}
	public int getUnits()
	{
		return this.units;
	}
	public ArrayList<String> getPreq()
	{
		return this.preq;
	}
	public String getClassCode() {
		return classCode;
	}
}
