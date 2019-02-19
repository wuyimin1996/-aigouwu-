package com.wu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.wu.util.ZqDBUtil;

//分类dao
public class CategoryDao {

	
	//添加 分类
	public int add(Map<String,Object> record){
		//判断是否添加成功
		int flag=-1;
		try{
			//获取连接
			Connection cn=ZqDBUtil.getConn();
			//发送sql语句
			Statement stmt=cn.createStatement();
			flag=stmt.executeUpdate("insert into category(name,sort) values('"+record.get("name")+"','"+record.get("sort")+"')");
			//关闭连接
			ZqDBUtil.close(cn, stmt, null);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return 1;
	}
	//查询分类
	public List<Map<String,Object>> list(){
		ResultSet rs=null;
		List<Map<String, Object>> list=null;
		try {
			//获取连接
			Connection cn=ZqDBUtil.getConn();
			//发送sql 语句
			Statement stmt=cn.createStatement();
			//传入sqL 语句
			rs = stmt.executeQuery("select * from category order by sort desc");
			
		
			//得到数据封装到list map 里面
			list = ZqDBUtil.getHashMap(rs);
			ZqDBUtil.close(cn, stmt, rs);
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	//查询单个分类
		public Map<String,Object> get(int categoryid){
			
			
			ResultSet rs=null;
			List<Map<String,Object>> records=null;
	    	try {
	            //获得连接
	    		Connection cn=ZqDBUtil.getConn();
	            //发送SQL语句
				Statement stmt=cn.createStatement();
				//下面这么写会存在SQL注入问题（不安全）
				rs=stmt.executeQuery("select * from category where categoryid='"+categoryid+"'");
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
