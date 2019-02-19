package com.wu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.wu.util.ZqDBUtil;

//产品评论dao 
public	 class CommentDao {
	
	public int add(Map<String,Object> reoctd){
		
		int flag=-1;
	
		try {
			
			Connection cn=(Connection) ZqDBUtil.getConn();
			//存在注入sql语句问题
			Statement stmt=cn.createStatement();
			flag=stmt.executeUpdate("insert into comment(title,content,score,time,productid,vipid) values('"+reoctd.get("title")+"','"+reoctd.get("content")+"','"+reoctd.get("score")+"','"+reoctd.get("time")+"','"+reoctd.get("productid")+"','"+reoctd.get("vipid")+"')");
			
			ZqDBUtil.close(cn, stmt, null);
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	//查询某一产品的所有评论
	public List<Map<String,Object>> getCommentsByProduct(int productid){
		//接收数据库传回来的信息
		ResultSet rs=null;	
		List<Map<String,Object>> records=null;
		
		
		try {
			Connection cn=(Connection) ZqDBUtil.getConn();
			//注入问题
			Statement stmt=cn.createStatement();
			
		//	String sql="select a.title,a.content,a.score,a.time,b.username from comment a,vip b where a.vipid=b.vipid and a.productid='"+productid+"'";
			String sql="select a.title,a.content,a.score,a.time,b.username from comment a,vip b where a.vipid=b.vipid and a.productid='"+productid+"'";

			rs=stmt.executeQuery(sql);
			System.out.println("执行的sql"+ sql);
			
			records=ZqDBUtil.getHashMap(rs);
			
			//关闭连接
			ZqDBUtil.close(cn, stmt, rs);
			
			return records;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return records;
	}
	
	//删除
		public int del(int commentid){
			//删除是否成功
			int flag=-1;
			
	    	try {
	            //获得连接
	    		Connection cn=(Connection) ZqDBUtil.getConn();
	            //发送SQL语句
				Statement stmt=cn.createStatement();
				//下面这么写会存在SQL注入问题（不安全）
				flag=stmt.executeUpdate("delete from comment where commentid='"+commentid+"'");
				
				//关闭连接
				ZqDBUtil.close(cn, stmt, null);
				
	            return flag;
			} catch (Exception e) {
				e.printStackTrace();
			}

			return flag;
		}
		
	
}
