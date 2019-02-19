package com.wu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.wu.util.ZqDBUtil;

//订单相关的
public class OrdersDao {

	   //生成订单，返回生成的订单编号 
	   public int makeOrder(Map<String,Object> order,List<Map<String,Object>> cart){
			//生成的订单ID
			int orderid= -1;
			//获得连接
 		Connection cn=null;
			
	    	try {
	            //获得连接
	    		cn=(Connection) ZqDBUtil.getConn();
	    		//开始事务
	    		cn.setAutoCommit(false);	    		
	            //发送SQL语句
				//Statement stmt=cn.createStatement();
	    		PreparedStatement pstmt=(PreparedStatement) cn.prepareStatement("insert into orders(addressid,payway,maketime,status,vipid) values(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, order.get("addressid").toString());
				pstmt.setString(2, order.get("payway").toString());
				pstmt.setString(3, order.get("maketime").toString());
				pstmt.setString(4, order.get("status").toString());
				pstmt.setString(5, order.get("vipid").toString());
	    		
	    		
				pstmt.executeUpdate();			
					
				ResultSet results=pstmt.getGeneratedKeys();
	            if(results.next()){
	            	orderid = results.getInt(1);
	            }
	                        
	            
	            //循环购物车
	    		for(int i=0;i<cart.size();i++){
	    			Map<String,Object> temp=cart.get(i);
	    			//产品ID
	    			int productid=Integer.parseInt(temp.get("productid").toString());
	    			//该产品购买的数量
	    			int quantity=Integer.parseInt(temp.get("buycount").toString());	
	    			
	    			pstmt=(PreparedStatement) cn.prepareStatement("insert into orderdetail(orderid,productid,quantity) values(?,?,?)");
	    			pstmt.setInt(1, orderid);
	    			pstmt.setInt(2, productid);
	    			pstmt.setInt(3, quantity);	
	    			
	    			pstmt.executeUpdate();	
	    		}
	            //提交事务
	    		cn.commit();
	    		           			
				//关闭连接
				ZqDBUtil.close(cn, pstmt, results);
				
	            return orderid;
			} catch (Exception e) {
				e.printStackTrace();
				try {
					//回滚事务
					cn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			
			
			return orderid;
		   
		   
		   
	   }
	  
	   //连接订单表和客户表，查询相关信息
		 public Map<String,Object> getOrdersAndVipWithdId(int orderid){
			ResultSet rs=null;
			
			List<Map<String,Object>> records=null;
			
	    	try {
	            //获得连接
	    		Connection cn=(Connection) ZqDBUtil.getConn();
	            //发送SQL语句
				Statement stmt=cn.createStatement();
				//下面这么写会存在SQL注入问题（不安全）
				rs=stmt.executeQuery("select c.addressname,c.postcode,c.receiver,c.phone,c.bz,d.* from address c,(select a.*,b.username from orders a,vip b where a.vipid=b.vipid and orderid='"+orderid+"') d where c.addressid=d.addressid");
				
				records=ZqDBUtil.getHashMap(rs);
				
				//关闭连接
				ZqDBUtil.close(cn, stmt, rs);
				
				return records.get(0);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			return null;
		}
		 //连接订单明细表和产品表，查询相关信息
		 public List<Map<String,Object>> getOrderdetailAndProductWithdId(int orderid){
			ResultSet rs=null;
			
			List<Map<String,Object>> records=null;
			
	    	try {
	            //获得连接
	    		Connection cn=(Connection) ZqDBUtil.getConn();
	            //发送SQL语句
				Statement stmt=cn.createStatement();
				//下面这么写会存在SQL注入问题（不安全）
				rs=stmt.executeQuery("select a.orderid,a.quantity,round(a.quantity*b.myprice,1) total,b.* from orderdetail a,product b where a.productid=b.productid and a.orderid='"+orderid+"'");
				
				records=ZqDBUtil.getHashMap(rs);
				
				//关闭连接
				ZqDBUtil.close(cn, stmt, rs);
				
				return records;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			return records;
		}
		
		
		 //根据订单id 查出该订单的总价
		 public String getOrderSumWithId(int orderid){
			ResultSet rs=null;
			
			List<Map<String,Object>> records=null;
			
	    	try {
	            //获得连接
	    		Connection cn=(Connection) ZqDBUtil.getConn();
	            //发送SQL语句
				Statement stmt=cn.createStatement();
				//下面这么写会存在SQL注入问题（不安全）
				rs=stmt.executeQuery("select round(sum(a.quantity*b.myprice),1) sum from orderdetail a,product b where a.productid=b.productid and a.orderid='"+orderid+"'");
				
				records=ZqDBUtil.getHashMap(rs);
				
				//关闭连接
				ZqDBUtil.close(cn, stmt, rs);
				
				return records.get(0).get("sum").toString();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    		return "";
		}
		 public int updatestatus(String status,int orderid){
			 //判断是否修改成功
			 int flag=-1;
			 
			
			 try {
				 //获取连接
				 Connection cn=(Connection) ZqDBUtil.getConn();
				 //传送sql与语句
				 Statement stmt=cn.createStatement();
				 
				 flag=stmt.executeUpdate("update orders set status='"+status+"' where orderid='"+orderid+"'");
				 
				ZqDBUtil.close(cn, stmt, null);
				return flag;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return flag;
		 }
		 
}
