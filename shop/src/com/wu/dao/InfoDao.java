package com.wu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.wu.util.ZqDBUtil;

//帮助信息相关的dao
public class InfoDao {
 
	public int add(Map<String ,Object> record){
		int flag=-1;
		
		
		try {
			Connection cn=(Connection) ZqDBUtil.getConn();
			//sql 注入问题
			Statement stmt=cn.createStatement();
			flag=stmt.executeUpdate("insert into info(title,time,publisher,lanmu,context)  values('"+record.get("title")+"','"+record.get("time")+"','"+record.get("publisher")+"','"+record.get("lanmu")+"','"+record.get("context")+"')");
			
			ZqDBUtil.close(cn, stmt, null);
			return flag;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	
	//获取某一分类的信息
	 public List<Map<String,Object>> listByLanmu(String lanmu){
		 
		 List<Map<String,Object>> records=null;
		 
		 ResultSet rs=null;
	 try {
			 
			 Connection cn=(Connection) ZqDBUtil.getConn();

			Statement stmt=cn.createStatement();
			
			rs=stmt.executeQuery("select * from info  where lanmu='"+lanmu+"'");
			
			records=ZqDBUtil.getHashMap(rs);
			
			ZqDBUtil.close(cn, stmt, rs);
			return records;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 		return records;
		 
	 }
	//查询单个信息
			public Map<String,Object> get(int infoid){
				
				
				ResultSet rs=null;
				List<Map<String,Object>> records=null;
		    	try {
		            //获得连接
		    		Connection cn=(Connection) ZqDBUtil.getConn();
		            //发送SQL语句
					Statement stmt=cn.createStatement();
					//下面这么写会存在SQL注入问题（不安全）
					rs=stmt.executeQuery("select * from info where categoryid='"+infoid+"'");
				//	System.out.println("1111111111111111111111111111111111111111111111111");
					records=ZqDBUtil.getHashMap(rs);					
					//关闭连接
					ZqDBUtil.close(cn, stmt, rs);
					
		            return records.get(0);
		            
				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}
}
