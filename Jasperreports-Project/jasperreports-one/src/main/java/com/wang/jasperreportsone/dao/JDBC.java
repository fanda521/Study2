package com.wang.jasperreportsone.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC {
	public static String driver="com.mysql.jdbc.Driver";
	public static String url="jdbc:mysql://127.0.0.1:3306/jasperreports?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
	public static String user="root";
	public static String pwd="331224";
	public static Connection conn=getConnection();
	public static Statement statement=getStatement();
 
	private static Connection getConnection(){
		if(conn==null){
			try{
				 Class.forName(driver);
				 conn= DriverManager.getConnection(url,user,pwd);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return conn;
	}
	private static Statement getStatement(){
		if(statement==null){
			try{
				statement=conn.createStatement();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return statement;
	}
	public static ResultSet getResultSet(String sql){
		ResultSet rs=null;
		try{
			rs=statement.executeQuery(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}
	public static void closeAll(ResultSet rs,Statement st,Connection cn){
		try{
			if(rs!=null){
				rs.close();
			}
			if(st!=null){
				st.close();
			}
			if(cn!=null){
				cn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

