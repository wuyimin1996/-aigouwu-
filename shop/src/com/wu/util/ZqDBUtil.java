package com.wu.util;


import java.io.InputStream;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
/**
 * 数据库相关操作工具类
 */
public class ZqDBUtil {
	
	
	//将ResultSet转换为List
	public static List<Map<String,Object>> getHashMap(ResultSet rs) throws SQLException{

		List<Map<String,Object>> dataTable = new ArrayList<Map<String,Object>> ();
		ResultSetMetaData rsmd = rs.getMetaData();
		
		while(rs.next()){
			Map<String,Object> item = new HashMap<String,Object>();
			for(int i=1; i<=rsmd.getColumnCount();i++){
					if(rsmd.getColumnTypeName(i).equals("CLOB")){
							Clob colClob = rs.getClob(i);
							if(colClob == null){
								putToHash(item, rsmd.getColumnLabel(i).toLowerCase(), "");
							}else{
								putToHash(item, rsmd.getColumnLabel(i).toLowerCase(), getClobtoString(rs.getClob(i)));
							}
					}else{
						putToHash(item, rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
					}
				//	System.out.println(""当前列的名字:"+rsmd.getColumnLabel(i)");
			}
			dataTable.add(item);
		}
		return dataTable;
	}
	
	private static void putToHash(Map<String,Object> item,String key,String value){
			if(value!= null){
				item.put(key, value);
			}
	}	
	private static String getClobtoString(Clob clob) throws SQLException{
		return clob.getSubString(((long)1),((int)(clob.length())));
	}
	
	
	
	//读取配置文件
	 public static String getPara(String ParaName) 
		{
			//创建一个配置文件对象
		    Properties prop= new Properties();
			try
			{   
				//载入配置文件（读取流的过程）
				InputStream is = ZqDBUtil.class.getResourceAsStream("../../../DBConfig.property");
				//将配置文件的内容载入到配置文件对象中
				prop.load(is);
				//关闭流
				if(is!=null) is.close();
			}catch(Exception e) {
				System.out.println("there is error to read config file...");
				e.printStackTrace();
			}
			//从配置文件对象中找到指定参数的值
			return prop.getProperty(ParaName);
		}
	   
	   /**
	    * 建立连接
	    */
	   public static Connection getConn() {
			Connection conn = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(getPara("MySQLURL"));
			} catch (Exception e) {
				System.out.println("建立连接失败");
				e.printStackTrace();
			}
			
			return conn;
		}
	   /**
	    * 关闭连接
	    */
	   public static void close(Connection cn,Statement stmt,ResultSet rs) {
			try {
				if(rs != null) {
					rs.close();
					rs = null;
				}
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}		
				if(cn!= null) {
					cn.close();
					cn = null;
				}			
				
			} catch (Exception e) {
				System.out.println("关闭连接失败");
				e.printStackTrace();
			}
		}
	
}
