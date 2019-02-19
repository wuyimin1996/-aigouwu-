package com.wu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.wu.util.ZqDBUtil;

//用户相关的 dao
public class AdminDao {

	//验证用户名 和密码是否存在
	public Map<String,Object> validateVip(String username,String password){
		try {
            //获得连接
    		Connection cn=(Connection) ZqDBUtil.getConn();
            //发送SQL语句
			Statement stmt=cn.createStatement();
			//下面这么写会存在SQL注入问题（不安全）
			String sql="select * from admin where username='"+username+"' and password='"+password+"'";
			
			ResultSet rs=stmt.executeQuery(sql);
			System.out.println("执行的SQL："+sql);
			
			List<Map<String,Object>> records=ZqDBUtil.getHashMap(rs);
										
			//关闭连接
			ZqDBUtil.close(cn, stmt, rs);
			
			return records.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}
}
