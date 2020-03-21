package jdbc_postgress;

import java.sql.*;
import java.util.Scanner;

public class db {
	
	public static void main(String args[]) throws Exception
	{
		int n=0;
		getConnection();
		Scanner s=new Scanner(System.in);
		while(n!=6)
		{
			System.out.println("Enter your choice\n**********************************************\n1.Insert data\n2.retrive data\n3.update data\n4.delete data \n5.exit\n**************************************\n");
			n=s.nextInt();
			switch(n)
			{
			
			case 1:insert();
			break;
			case 2:retrive();
			break;
			case 3:update();
			break;
			case 4:deletew();
			break;
			}
			
		}
		
		
		
		
		
	}
	public static Connection getConnection() throws Exception
	{
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/HM", "postgres", "shashan");
			System.out.println("Connected successfull with pg admin");
			return conn;
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}
	public static  void insert() throws Exception
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter name,amount,id,no. of items");
		String s=sc.next();
		int i=sc.nextInt();
		int j=sc.nextInt();
		int m=sc.nextInt();
		
		try
		{
			
			Connection conn=getConnection();
			PreparedStatement pr=conn.prepareStatement("INSERT INTO service.counter VALUES('"+s+"',"+i+","+j+","+m+")");
			pr.executeUpdate();
			System.out.println("insertion is success");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public static void retrive() throws Exception
	{
		try
		{
			
		
		Connection conn=getConnection();
		PreparedStatement p=conn.prepareStatement("select * from service.counter");
		ResultSet r=p.executeQuery();
		while(r.next())
		{
			
			System.out.println("Name"+"        \t"+"Amount"+"\t"+"ID"+"\t"+"No_of_items");
			System.out.println(r.getString(1)+"  \t"+r.getInt(2)+"\t"+r.getInt(3)+"\t"+r.getInt(4));
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
	
	}
	public static void deletew() throws Exception
	{
		Scanner sn=new Scanner(System.in);
		System.out.println("enter name to be delete that tupple");
		String s=sn.next();
		try
		{
			Connection conn=getConnection();
			PreparedStatement p=conn.prepareStatement("DELETE FROM service.counter where(name=?)");
			p.setString(1,s);
			p.executeUpdate();
				
			System.out.println("data is deleted");
		}
		catch(Exception e)
		{

			System.out.println(e);
		}
	}
	public static void update() throws Exception
	{
		Scanner sn=new Scanner(System.in);
		System.out.println("enter the new name to be updated and id of old tupple");
		String s=sn.next();
		int m=sn.nextInt();
		try
		{
			
			
			Connection conn=getConnection();
			
			PreparedStatement p=conn.prepareStatement("update service.counter  set name='"+s+"' where id="+m);
			
			p.executeUpdate();
			
		}
		catch(Exception e)
		{

			System.out.println(e);
		}
	}

}
