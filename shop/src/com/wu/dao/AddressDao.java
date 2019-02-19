package com.wu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;

import com.wu.util.QueryResult;
import com.wu.util.ZqDBUtil;


//用户地址信息
public class AddressDao {
		
	//查询所有商品
			public List<Map<String,Object>> listByVipid(int vipid){
				  //记录总数

				ResultSet rs=null;
				//QueryResult qr=new QueryResult();
				List<Map<String,Object>> records=null;
				try {
				   //获取连接
				   Connection cn=(Connection) ZqDBUtil.getConn();
				   //发送sql 语句
				   Statement stmt=cn.createStatement();
					rs=stmt.executeQuery("select * from address where vipid='"+vipid+"'");
				   
				   records=ZqDBUtil.getHashMap(rs);
				   return records;
				}catch (Exception e) {
				
				}
				return records;
			}
				
			//添加地址
			public  int add(Map<String,Object> record){
				//添加是否成功
				int  flag=-1;
				
				try {
					//获得连接
					Connection cn=(Connection) ZqDBUtil.getConn();
					//发送sql语句
					Statement stmt=cn.createStatement();
					
					//sql语句
					flag=stmt.executeUpdate("insert into address(addressname,postcode,receiver,phone,vipid,bz) values('"+record.get("addressname")+"','"+record.get("postcode")+"','"+record.get("receiver")+"','"+record.get("phone")+"','"+record.get("vipid")+"','"+record.get("bz")+"')");

					
					ZqDBUtil.close(cn, stmt,null);
					return flag;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return flag;
			}
			//查询一条数据
			public Map<String,Object> get(int addressid){
				
				ResultSet rs=null;
				List<Map<String,Object>> records=null;
			try {
				// 获得连接
				Connection cn = (Connection) ZqDBUtil.getConn();
				// 发送SQL语句
				Statement stmt = cn.createStatement();
				// 下面这么写会存在SQL注入问题（不安全）
				rs=stmt.executeQuery("select * from address where addressid='"+addressid+"'");
				// 关闭连接
				records =ZqDBUtil.getHashMap(rs);
				ZqDBUtil.close(cn, stmt, rs);

				return records.get(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
			//查询会员的地址
			public QueryResult listAddressWithOrderby(int startIndex,int pageSize,String where,String orderby){
				ResultSet rs=null;
				
				List<Map<String,Object>> records=null;
				
		    	try {
		            //获得连接
		    		Connection cn=(Connection) ZqDBUtil.getConn();
		            //发送SQL语句
					Statement stmt=cn.createStatement();
					//下面这么写会存在SQL注入问题（不安全）		
					String sql="select a.*,b.username from address a,vip b where a.vipid=b.vipid"+where+orderby+" limit "+startIndex+","+pageSize;
					System.out.println("执行的SQL："+sql);
					
					rs=stmt.executeQuery(sql);	
					
					records=ZqDBUtil.getHashMap(rs);
					
					
					//查询总记录数		
					String sql2="select count(*) from address a,vip b where a.vipid=b.vipid"+where;
					rs=stmt.executeQuery(sql2);
					
					//记录总数
					int totalCount=0;
					//移动指针，指向第一条
					if(rs.next()){
						//获取第一列数据
						totalCount=rs.getInt(1);
						
					}
					System.out.println("记录总数为："+totalCount);
					
					//将数据和记录数送给QueryResult			
					QueryResult qr=new QueryResult();
					qr.setRecords(records);
					qr.setTotalCount(totalCount);
							
					//关闭连接
					ZqDBUtil.close(cn, stmt, rs);
					
					return qr;
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}
			
			
			//删除
			public int del(int addressid){
				//删除是否成功
				int flag=-1;
				
		    	try {
		            //获得连接
		    		Connection cn=(Connection) ZqDBUtil.getConn();
		            //发送SQL语句
					Statement stmt=cn.createStatement();
					//下面这么写会存在SQL注入问题（不安全）
					flag=stmt.executeUpdate("delete from address where addressid='"+addressid+"'");
					
					//关闭连接
					ZqDBUtil.close(cn, stmt, null);
					
		            return flag;
				} catch (Exception e) {
					e.printStackTrace();
				}

				return flag;
			}
			
		//判断某一用户是否拥有某一地址id
			public boolean validategetByVidAndAddressId(int vipid,int addressid){
				ResultSet rs=null;
				List<Map<String,Object>> records=null;
				
				try {
					//获得连接
					Connection cn=(Connection) ZqDBUtil.getConn();

					Statement stmt=cn.createStatement();
					rs=stmt.executeQuery("select * from address where vipid='"+vipid+"' and addressid='"+addressid+"'");
					records=ZqDBUtil.getHashMap(rs);
					
					ZqDBUtil.close(cn, stmt, rs);
					if(records.size()>0){
						return true;
					}else{
						return false;
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
			
		//更新地址信息	
			public int update(Map<String,Object> record){
				//是否成功
				int flag=-1;			
		    	try {
		            //获得连接
		    		Connection cn=(Connection) ZqDBUtil.getConn();
		            //发送SQL语句
					Statement stmt=cn.createStatement();
					//下面这么写会存在SQL注入问题（不安全）
					flag=stmt.executeUpdate("update address set addressname='"+record.get("addressname")+"',postcode='"+record.get("postcode")+"',receiver='"+record.get("receiver")+"',phone='"+record.get("phone")+"',bz='"+record.get("bz")+"' where  addressid='"+record.get("addressid")+"'");
					
					//关闭连接
					ZqDBUtil.close(cn, stmt, null);
					
		            return flag;
				} catch (Exception e) {
					e.printStackTrace();
				}		
				return flag;
			}
			
			
			
}