package com.wu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.wu.util.ZqDBUtil;

//用户相关的dao
public class VipDao {
	
	//验证用户是否存在
		public Map<String,Object> validateVip(String username,String password){
			       
			
	    	try {
	            //获得连接
	    		Connection cn=ZqDBUtil.getConn();
	            //发送SQL语句
				Statement stmt=cn.createStatement();
				//下面这么写会存在SQL注入问题（不安全）
				String sql="select * from vip where username='"+username+"' and password='"+password+"'";
				
				ResultSet rs=stmt.executeQuery(sql);
				System.out.println("执行的SQL："+sql);
				
				List<Map<String,Object>> records=ZqDBUtil.getHashMap(rs);
											
				//关闭连接
				ZqDBUtil.close(cn, stmt, rs);
				if(records.size()>0){
					 return records.get(0);
				}else{
					return null;
				}         
				
			} catch (Exception e) {
				e.printStackTrace();
			}				
			return null;
		}
	//用户登陆 更新会员表的某些字段
	public int 	update(Map<String,Object> record){
		int flag=-1;
		try {
			//建立连接
			Connection cn=(Connection) ZqDBUtil.getConn();
			//传送sql语句
			Statement stmt=cn.createStatement();
			
			String sql="update vip set ";
			//判断有没有更新passowrd
			if(record.get("password")!=null && !record.get("password").equals("")){
				sql+="password='"+record.get("password")+"',";
			}
			//更新字段
			if(record.get("sex")!=null && !record.get("sex").equals("")){
				sql+="sex='"+record.get("sex")+"',";
			}
			if(record.get("email")!=null && !record.get("email").equals("")){
				sql+="email='"+record.get("email")+"',";
			}
			if(record.get("phone")!=null && !record.get("phone").equals("")){
				sql+="phone='"+record.get("phone")+"',";
			}
			if(record.get("score")!=null && !record.get("score").equals("")){
				sql+="score='"+record.get("score")+"',";
			}
			if(record.get("question")!=null && !record.get("question").equals("")){
				sql+="question='"+record.get("question")+"',";
			}
			if(record.get("answer")!=null && !record.get("answer").equals("")){
				sql+="answer='"+record.get("answer")+"',";
			}
			if(record.get("lastlogintime")!=null && !record.get("lastlogintime").equals("")){
				sql+="lastlogintime='"+record.get("lastlogintime")+"',";
			}
			//去掉最后一个多余的,
			sql=sql.substring(0,sql.lastIndexOf(","));
			sql+="where vipid='"+record.get("vipid")+"'";
			//update  vip set
			System.out.println("拼成的sql +///////////////////"+sql);
			
			
			flag=stmt.executeUpdate(sql);
			ZqDBUtil.close(cn, stmt, null);
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag; 
	}
	//根据用户名查询用户
		public Map<String,Object> getByusername(String username){
			
			
			ResultSet rs=null;
			List<Map<String,Object>> records=null;
	    	try {
	            //获得连接
	    		Connection cn=ZqDBUtil.getConn();
	            //发送SQL语句
				Statement stmt=cn.createStatement();
				//下面这么写会存在SQL注入问题（不安全）
				rs=stmt.executeQuery("select * from vip where username='"+username+"'");
				records=ZqDBUtil.getHashMap(rs);					
				//关闭连接
				ZqDBUtil.close(cn, stmt, rs);
				//IndexOutOfExcept  下标越界  很容易出现 如果没有出现
				if(records.size()>0){
					 return records.get(0);
				}else{
					return null;
				}             
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return null;
			
		}
		
		
		//添加会员
		public int addVip(Map<String,Object> record){
			//添加是否成功
			int flag=-1;
			
	    	try {
	            //获得连接
	    		Connection cn=ZqDBUtil.getConn();
	            //发送SQL语句
				Statement stmt=cn.createStatement();
				//下面这么写会存在SQL注入问题（不安全）
				flag=stmt.executeUpdate("insert into vip(username,password,sex,email,phone,score,question,answer,lastlogintime) values('"+record.get("username")+"','"+record.get("password")+"','"+record.get("sex")+"','"+record.get("email")+"','"+record.get("phone")+"','"+record.get("score")+"','"+record.get("question")+"','"+record.get("answer")+"','"+record.get("lastlogintime")+"')");
				
				//关闭连接
				ZqDBUtil.close(cn, stmt, null);
				
	            return flag;
			} catch (Exception e) {
				e.printStackTrace();
			}	
			return flag;
		}
		

		//验证答案是否正确
		public Map<String,Object> validateAnswer(String username,String question,String answer){
			
			
			ResultSet rs=null;
			List<Map<String,Object>> records=null;
	    	try {
	            //获得连接
	    		Connection cn=ZqDBUtil.getConn();
	            //发送SQL语句
				Statement stmt=cn.createStatement();
				//下面这么写会存在SQL注入问题（不安全）
				rs=stmt.executeQuery("select * from vip where username='"+username+"' and question='"+question+"' and answer='"+answer+"'");
				records=ZqDBUtil.getHashMap(rs);					
				//关闭连接
				ZqDBUtil.close(cn, stmt, rs);
				
	            return records.get(0);
	            
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}
			
		//查询单条
		public Map<String,Object> get(int vipid){
			
			
			ResultSet rs=null;
			List<Map<String,Object>> records=null;
	    	try {
	            //获得连接
	    		Connection cn=ZqDBUtil.getConn();
	            //发送SQL语句
				Statement stmt=cn.createStatement();
				//下面这么写会存在SQL注入问题（不安全）
				rs=stmt.executeQuery("select * from vip where vipid='"+vipid+"'");
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
