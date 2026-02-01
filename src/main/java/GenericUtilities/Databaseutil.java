package GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.Assert;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import propertyfileConfig.ObjectReader;

/**
 * @author:Aditya K.
 * @Description : This class is used to perform database actions
 */

public class Databaseutil
{	
	static Statement statment;
	static Connection connection;
	ResultSet Result;
	int ModifyResult;
	static Session jumpHostSession = null;
	
	/**
	 * @Description : This method is used to connect Database server
	 */

    public static void connectdatabase() throws Throwable {

    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.setLoginTimeout(10);
                        
            String username = System.getenv("username");  
            String password = System.getenv("password");  
            String url = System.getenv("url");
            connection = DriverManager.getConnection(url, username, password);              
            statment = connection.createStatement();
            System.out.println("DB Connection Successfull");
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("DataBase Connection was not successfull");
        }
    }

	/**
	 * @Description : This method is used to execute query and validate data in database
	 */
	public void ExecuteQuery(String Query,String data,int index)

	{
		boolean flag = false;
		try {

			ResultSet result = statment.executeQuery(Query);

			while (result.next()) {
				String actualData = result.getString(index);

				if (actualData.equals(data)) {
					flag = true;
					break;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		Assert.assertEquals(flag, true);


	}
	//Another Method

	/**
	 * @Description: This method is used to execute the query and return the result as true/false
	 *
	 * @param Query
	 * @param data
	 * @param index
	 * @return result
	 * @throws SQLException
	 */
	public boolean verifyData(String Query,String data,int index)
	{

		boolean flag=false;

		try {
			ResultSet result = statment.executeQuery(Query);
			while (result.next()) {
				String actualData = result.getString(index);

				if (actualData.equals(data)) {
					flag = true;
					//System.out.println(actualData);
					break;
				}
			}
		}catch(Exception e){

			e.printStackTrace();
		}
		finally {
			return flag;
		}
	}
	/**
	 * This method will Execute the Query
	 * @param Query
	 */
	public void executeQuerytoread(String Query)
	{
		try {
			Result = statment.executeQuery(Query);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * This method will Execute the  Query & return the result set
	 * @param Query
	 */
	public ResultSet executeQuery(String Query)
	{
		Result=null;
		try {
			Result = statment.executeQuery(Query);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return Result;
	}
	/**
	 * This method will Execute the update Query
	 * @param Query
	 */
	public int executeUpdateQuery(String Query)
	{
		int row=0;
		try {
			row = statment.executeUpdate(Query);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return row;
	}

	/**
	 * This method will Execute the  Query & return the result set
	 * @param resultSet
	 *  @param ColumnName
	 */
	public String fetchDataBycolumn(ResultSet resultSet,String ColumnName)  {
		String name = null;
		try {

			while (resultSet.next()) {
				name = resultSet.getString(ColumnName);
			}
			//System.out.println(name);
		}catch(Exception e){
			e.printStackTrace();
		}
		return name;

	}

	public String returnValueByColumnname(String Query,String ColumnName)
	{
		String name = null;
		try {
			ResultSet resultSet = statment.executeQuery(Query);


			while (resultSet.next()) {
				name = resultSet.getString(ColumnName);
				break;
			}
			//System.out.println(name);
		}catch (Exception e) {
		}
		return name;

	}


	public static  String getTableName(String query)
	{
		String[] arr = query.replaceAll("( )+"," ").split(" ");
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equalsIgnoreCase("from")){
				return arr[i+1];
			}
		}
		return "";
	}

	public static String getQuery(String propertyFileKey, String whereCondition){
		String query = String.format(ThreadLocalClass.getpropfile().getPropertiesData(propertyFileKey), whereCondition);
		return query;
	}


	/**
	 * This Method will give data based on the column name
	 * @param coloumnName
	 */
	public void readDatafromDB(String coloumnName)
	{
		try {
			while(Result.next())
			{
				System.out.println(Result.getString(coloumnName));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This Method will write the data into DataBase by Executing Query
	 * @param Query
	 */
	public void WriteDatatoDataBase(String Query)
	{
		try {
			ModifyResult = statment.executeUpdate(Query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ModifyResult>=1)
		{
			System.out.println("DataBase Updated");
		}

	}
	/**
	 * This Method is used to close the statement and connection
	 * @throws SQLException
	 */
	public void closedatabase() throws SQLException

	{

		statment.close();
		connection.close();
	}

	/**
	 * This Method is used to Check the connection status
	 */

	public boolean isDbConnected() {
		boolean flag=false;
		try {
			if (connection != null && !connection.isClosed())
				flag= true;
			else
				flag= false;
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}
}

