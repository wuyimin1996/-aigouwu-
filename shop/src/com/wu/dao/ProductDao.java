package com.wu.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.wu.util.QueryResult;
import com.wu.util.ZqDBUtil;

//产品相关的dao
public class ProductDao {
	
	//添加产品
		public int add(Map<String,Object> record){
			//添加是否成功
			int flag=-1;			
	    	try {
	            //获得连接
	    		Connection cn=ZqDBUtil.getConn();
	            //发送SQL语句
				Statement stmt=cn.createStatement();
				//下面这么写会存在SQL注入问题（不安全）
				flag=stmt.executeUpdate("insert into product(name,myprice,marketprice,storecount,hit,time,photo,categoryid,content) values('"+record.get("name")+"','"+record.get("myprice")+"','"+record.get("marketprice")+"','"+record.get("storecount")+"','"+record.get("hit")+"','"+record.get("time")+"','"+record.get("photo")+"','"+record.get("categoryid")+"','"+record.get("content")+"')");
				
				//关闭连接
				ZqDBUtil.close(cn, stmt, null);
				
	            return flag;
			} catch (Exception e) {
				e.printStackTrace();
			}		
			return flag;
		}
		//查询所有商品
		public QueryResult list(int startIndex,int pageSize){
			  //记录总数

			ResultSet rs=null;
			QueryResult qr=new QueryResult();
			List<Map<String,Object>> records=null;
			try {
			   //获取连接
			   Connection cn=ZqDBUtil.getConn();
			   //发送sql 语句
			   Statement stmt=cn.createStatement();
				rs=stmt.executeQuery("select a.productid,a.name productname,a.myprice,a.marketprice,a.storecount,a.hit,a.time,a.photo,a.categoryid,b.name categoryname from product a,category b where a.categoryid=b.categoryid order by time desc limit "+startIndex+","+pageSize+"");
			   
			   records=ZqDBUtil.getHashMap(rs);
			   
			   
			   //查询总记录数
			  String sql2="select count(*) from product a,category b where a.categoryid=b.categoryid";
			  rs= stmt.executeQuery(sql2);
			  
				int totalCount=0;
			  //移动指针指向第一条
			  if(rs.next()){
				  totalCount=rs.getInt(1);
			  }
			  //封装数据
			   qr.setRecords(records);
			   qr.setTotalCount(totalCount);
			  //关闭连接
			   ZqDBUtil.close(cn, stmt, rs);
			   //返回值
			  return qr;
			   	
			} catch (SQLException e) {
			// TODO Auto-generated catch block
		   		e.printStackTrace();
		   	}
		
			return null;
		}
	
		
		//删除操作
		public int delete(int productid){
			//删除是否成功
			int flag=-1;
			
	    	try {
	            //获得连接
	    		Connection cn=ZqDBUtil.getConn();
	            //发送SQL语句
				Statement stmt=cn.createStatement();
				//下面这么写会存在SQL注入问题（不安全）
				flag=stmt.executeUpdate("delete from product where productid='"+productid+"'");
				
				//关闭连接
				ZqDBUtil.close(cn, stmt, null);
				
	            return flag;
			} catch (Exception e) {
				e.printStackTrace();
			}

			return flag;
		}
		//查询一条数据
		public Map<String,Object> get(int productid){
			
			ResultSet rs=null;
			List<Map<String,Object>> records=null;
		try {
			// 获得连接
			Connection cn = ZqDBUtil.getConn();
			// 发送SQL语句
			Statement stmt = cn.createStatement();
			// 下面这么写会存在SQL注入问题（不安全）
			rs=stmt.executeQuery("select * from product where productid='"+productid+"'");
			// 关闭连接
			records =ZqDBUtil.getHashMap(rs);
			ZqDBUtil.close(cn, stmt, null);

			return records.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	//修改上商品
		
		public int update(Map<String,Object> record){
			//是否成功
			int flag=-1;			
	    	try {
	            //获得连接
	    		Connection cn=ZqDBUtil.getConn();
	            //发送SQL语句
				Statement stmt=cn.createStatement();
				//下面这么写会存在SQL注入问题（不安全）
				flag=stmt.executeUpdate("update product set name='"+record.get("name")+"',myprice='"+record.get("myprice")+"',marketprice='"+record.get("marketprice")+"',storecount='"+record.get("storecount")+"',hit='"+record.get("hit")+"',time='"+record.get("time")+"',photo='"+record.get("photo")+"',categoryid='"+record.get("categoryid")+"' where productid='"+record.get("productid")+"'");
				
				//关闭连接
				ZqDBUtil.close(cn, stmt, null);
				
	            return flag;
			} catch (Exception e) {
				e.printStackTrace();
			}		
			return flag;
		}
		
		//按照条件查询  （时间） 热度等 按照大小 长度
		public QueryResult listWithOrderby(int startIndex, int pageSize,String where,
				String orderby) {			
			//设置结果集
			ResultSet rs=null;
			
			List<Map<String,Object>> records=null;
			try {
				//设置连接
				Connection cn=ZqDBUtil.getConn();
				//发送sql语句
				Statement stmt=cn.createStatement();
				String sql="select a.productid,a.name productname,a.myprice,a.marketprice,a.storecount,a.hit,a.time,a.photo,a.categoryid,b.name categoryname from product a,category b where a.categoryid=b.categoryid"+where+orderby+" limit "+startIndex+","+pageSize;
				//接收数据
				rs=stmt.executeQuery(sql);
				System.out.println("执行sql ：" +sql);
				records=ZqDBUtil.getHashMap(rs);
				
				//查询记录总数
				String sql2="select count(*) from product a,category b where a.categoryid=b.categoryid"+where;
				rs=stmt.executeQuery(sql2);
				//记录总数
				int totalCount =0;
				//移动指针，指向第一条
				if(rs.next()){
					totalCount=rs.getInt(1);
				}
				System.out.println("总记录数："+totalCount);
				
				//将数据和记录数传给QuerResult
				QueryResult qr=new QueryResult();
				
				qr.setRecords(records);
				qr.setTotalCount(totalCount);
				
				//关闭连接
				ZqDBUtil.close(cn, stmt, rs);
				return qr;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		
		//根据订单状态 查询 订单列表
		public QueryResult listOrdersWithWhere(int startIndex,int pageSize,String where ,String orderby){
			ResultSet rs=null;
			
			List<Map<String,Object>> records=null;
			
	    	try {
	            //获得连接
	    		Connection cn=ZqDBUtil.getConn();
	            //发送SQL语句
				Statement stmt=cn.createStatement();
							
				String sql="select e.*,f.total from";
				   
				sql+="(select a.*,b.username from orders a,vip b where a.vipid=b.vipid) e,";
				   
				sql+="(select round(sum(c.quantity*d.myprice),1) total,c.orderid from orderdetail c,product d where c.productid=d.productid GROUP BY c.orderid) f";
				    
				sql+=" where e.orderid=f.orderid"+where+orderby+" limit "+startIndex+","+pageSize;
				
				
				System.out.println("执行的SQL："+sql);
							
				rs=stmt.executeQuery(sql);
				
				
				records=ZqDBUtil.getHashMap(rs);
				
				
				//查询总记录数					
				String sql2="select count(*) from";
				   
				sql2+="(select a.*,b.username from orders a,vip b where a.vipid=b.vipid) e,";
				   
				sql2+="(select sum(c.quantity*d.myprice) total,c.orderid from orderdetail c,product d where c.productid=d.productid GROUP BY c.orderid) f";
				    
				sql2+=" where e.orderid=f.orderid "+where;
				
				
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
		
		//查询商品的评论
	public QueryResult listCommentsWithOrderby(int startIndex, int pageSize,String where,
			String orderby) {			
					
					//设置结果集
					ResultSet rs=null;
					
					List<Map<String,Object>> records=null;
					try {
						//设置连接
						Connection cn=ZqDBUtil.getConn();
						//发送sql语句
						Statement stmt=cn.createStatement();
						String sql="select a.*,b.name productname from comment a,product b where a.productid=b.productid"+where+orderby+" limit "+startIndex+","+pageSize;
						//接收数据
						rs=stmt.executeQuery(sql);
						System.out.println("执行sql ：" +sql);
						records=ZqDBUtil.getHashMap(rs);
						
						//查询记录总数
						String sql2="select count(*) from comment a,product b where a.productid=b.productid"+where;
						rs=stmt.executeQuery(sql2);
						//记录总数
						int totalCount =0;
						//移动指针，指向第一条
						if(rs.next()){
							totalCount=rs.getInt(1);
						}
						System.out.println("总记录数："+totalCount);
						
						//将数据和记录数传给QuerResult
						QueryResult qr=new QueryResult();
						
						qr.setRecords(records);
						qr.setTotalCount(totalCount);
						
						//关闭连接
						ZqDBUtil.close(cn, stmt, rs);
						return qr;
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
				
		
			//搜索商品的通用查询
			public QueryResult searchWithWhere(int startIndex, int pageSize,String where,
					String orderby) {			
				//设置结果集
				ResultSet rs=null;
				
				List<Map<String,Object>> records=null;
				try {
					//设置连接
					Connection cn=ZqDBUtil.getConn();
					//发送sql语句
					Statement stmt=cn.createStatement();
					String sql="select a.productid,a.categoryid,a.name productname,a.myprice,a.photo, a.marketprice,b.name categoryname from product a,category b";
				    sql+=" where a.categoryid=b.categoryid"+where+orderby+" limit "+startIndex+","+pageSize;
				    System.out.println(" 搜查 执行的sql 语句为:"+sql);
				    
					//接收数据
					rs=stmt.executeQuery(sql);
					records=ZqDBUtil.getHashMap(rs);
					
					//查询记录总数
					//查询总记录数					
					String sql2="select count(*) from product a,category b";
					   			    
					sql2+=" where a.categoryid=b.categoryid"+where;
					rs=stmt.executeQuery(sql2);
					//记录总数
					int totalCount =0;
					//移动指针，指向第一条
					if(rs.next()){
						totalCount=rs.getInt(1);
					}
					System.out.println("总记录数："+totalCount);
					
					//将数据和记录数传给QuerResult
					QueryResult qr=new QueryResult();
					
					qr.setRecords(records);
					qr.setTotalCount(totalCount);
					
					//关闭连接
					ZqDBUtil.close(cn, stmt, rs);
					return qr;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
}
