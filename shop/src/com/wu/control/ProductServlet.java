package com.wu.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.wu.dao.AddressDao;
import com.wu.dao.CategoryDao;
import com.wu.dao.CommentDao;
import com.wu.dao.OrdersDao;
import com.wu.dao.ProductDao;
import com.wu.dao.VipDao;
import com.wu.util.BaseCalculate;
import com.wu.util.QueryResult;
import com.wu.util.ValidateLogin;
	

//商品相关 servlet
public class ProductServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //把所有的请求都转交给doPost
		doPost(request,response);
	}
	//处理方法
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//转码
		request.setCharacterEncoding("UTF-8");
		
		//action表示用来区别到底是什么操作
		String action=request.getParameter("action");
		
		//发布商品
		if(action.equals("publish")){
			publish(request,response);	
		}else if(action.equals("publishUI")){//进入发布页面
			publishUI(request,response);			
		}else if(action.equals("list")){
			list(request,response);			
		}else if(action.equals("delete")){
			delete(request,response);//删除产品
		}else if(action.equals("edit")){
			edit(request,response);//进入更新页面
		}else if(action.equals("update")){
			update(request,response);//更新操作
		}else if(action.equals("upload")){
			upload(request,response);//下载
		}else if(action.equals("displayProducts")){//前台展示页面
			displayProducts(request,response);		
		}else if(action.equals("showDetail")){
			showDetail(request,response);//展示前台产品  细节
		}else if(action.equals("addCart")){
			addCart(request,response);//添加产品到购物车
		}else if(action.equals("delCart")){
			delCart(request,response);//删除购物车里面的产品
		}else if(action.equals("showCart")){
			showCart(request,response);//进入购物车
		}else if(action.equals("toAddress")){
			toAddress(request,response);//进入配送地址
		}else if(action.equals("saveAddress")){
			saveAddress(request,response);//保存地址
		}else if(action.equals("toOrderConfirm")){
			toOrderConfirm(request,response);//进入订单确认页面
		} else if (action.equals("makeorder")) {
			makeorder(request, response);// 生产订单
		} else if (action.equals("queryorder")) {		
			queryorder(request, response);	// 管理人员查询订单
		}else if(action.equals("orderDetail")){
			orderDetail(request,response);//后台查看订单详细情况
		}else if(action.equals("updatestatus")){
			updatestatus(request,response);//更新订单状态
		}else if(action.equals("addComment")){
			addComment(request,response);//对商品进行评论
		}else if(action.equals("myorder")){//会员查看自己订单
			myorder(request,response);
		}else if(action.equals("mycomments")){//会员查看自己的评论
			mycomments(request,response);
		}else if(action.equals("delComments")){
			delComments(request,response);//删除评论
		}else if(action.equals("searchProduct")){
			searchProduct(request,response);//产品的搜查功能
		}else if(action.equals("myaddress")){
			myaddress(request,response);//会员查看自己地址
		}else if(action.equals("delAddress")){
			delAddress(request,response);//删除评论
		}else if(action.equals("editAddress")){
			editAddress(request,response);//修改地址 进入修改页面
		}else if(action.equals("updateAddress")){
			updateAddress(request,response); //更新地址修改
		}else if(action.equals("addAddressUI")){
			addAddressUI(request,response);//进入添加地址页面
		}else if(action.equals("addAddress")){
			addAddress(request,response); //保存地址
		}

	}
	//保存地址
	private void addAddress(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取用户输入的值
		String addressname = request.getParameter("addressname");
		String postcode = request.getParameter("postcode");
		String receiver = request.getParameter("receiver");
		String phone = request.getParameter("phone");
		String bz=request.getParameter("bz");
		//获取session
		HttpSession session = request.getSession();
		Map<String,Object> vip=(Map<String,Object>)session.getAttribute("vip");
		int vipid =Integer.parseInt(vip.get("vipid").toString());
	
		//组装数据
		Map<String ,Object> record=new HashMap<String, Object>();
		record.put("addressname", addressname);
		record.put("postcode", postcode);
		record.put("receiver", receiver);
		record.put("phone", phone);
		record.put("bz", bz);
		
		record.put("vipid", vipid);
		PrintWriter out =response.getWriter();
		//调用dao
		AddressDao addressDao=new AddressDao();
		int flag=addressDao.add(record);
		//返回乱码
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		if(flag>0){
		//	message="<script>window.location.href='productServlet?action=myaddress'</script>";
			out.print("1");
		}else{
			out.print("2");

		}
		
		
	}
	//进入添加地址页面
	private void addAddressUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("addAddress.jsp").forward(request, response);
		
	}
	//完成更新操作
	private void updateAddress(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Map<String,Object> vip=(Map<String, Object>) session.getAttribute("vip");
		
		//传过来来的 id
		String addressid = request.getParameter("addressid");
		
		//会员id 
		String vipid=vip.get("vipid").toString();
		
		AddressDao addressDao=new AddressDao();
		boolean flag = addressDao.validategetByVidAndAddressId(Integer.parseInt(vipid), Integer.parseInt(addressid));
		
		if(flag){
			//判断是不是这个用户
			String addressname = request.getParameter("addressname");
			String postcode = request.getParameter("postcode");
			String receiver = request.getParameter("receiver");
			String phone = request.getParameter("phone");
			String bz = request.getParameter("bz");
		
			//2.组装数据
			Map<String,Object> record =new HashMap<String, Object>();
			
			record.put("addressname", addressname);
			record.put("postcode", postcode);
			record.put("receiver", receiver);
			record.put("phone", phone);
			record.put("bz", bz);
			
			record.put("vipid", vipid);
			record.put("addressid", addressid);
		
			int flag2=addressDao.update(record);
			String message="";
			if(flag2>0){
				message="<script>window.location.href='productServlet?action=myaddress';</script>";
			}else{
				message="<script>window.location.href='productServlet?action=myaddress';</script>";			
			}
			request.setAttribute("message",message);
			request.getRequestDispatcher("message.jsp").forward(request, response);

		}else{//不允许用户编辑该地址		
			String message="<script>alert('请不要越权操作');window.location.href='productServlet?action=myaddress';</script>";
			//把信息放入页面
			request.setAttribute("message", message);

			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
			
		
	}
	//进入修改页面
	private void editAddress(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		//防止 前台用户互相操作   尽量避免直接传送参数；
		HttpSession session = request.getSession();
		Map<String,Object> vip = (Map<String, Object>) session.getAttribute("vip");
		
		String addressid = request.getParameter("addressid");
		int vipid=Integer.parseInt(vip.get("vipid").toString());
	
		AddressDao addressDao=new AddressDao();
		boolean flag = addressDao.validategetByVidAndAddressId(vipid, Integer.parseInt(addressid));
		if(flag){
			//允许用户编辑该地址
			Map<String,Object> record=addressDao.get(Integer.parseInt(addressid));
			request.setAttribute("record",record);
			request.getRequestDispatcher("editAddress.jsp").forward(request, response);
			
			
		}else{//不允许用户编辑该地址		
			String message="";
			message="<script>alert('请不要越权操作');window.location.href='productServlet?action=myaddress';</script>";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
		
		
	}
	//删除一地址
		private void delAddress(HttpServletRequest request,
				HttpServletResponse response)throws ServletException, IOException  {
	        
			//删除讨论的ID
			String addressid=request.getParameter("addressid");
			
			//调用DAO
			AddressDao addressDAO=new AddressDao();
			int flag=addressDAO.del(Integer.parseInt(addressid));
			String message="";
			if(flag>0){
				message="<script>alert('操作成功');window.location.href='productServlet?action=myaddress';</script>";
			}else{
				message="<script>alert('操作失败');window.location.href='productServlet?action=myaddress';</script>";
			}
			//把信息放入页面
			request.setAttribute("message", message);

			request.getRequestDispatcher("message.jsp").forward(request, response);

			
		}
	
	
	//会员查看自己地址
	private void myaddress(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//先获取Session 
		HttpSession session = request.getSession();
		Map<String,Object> vip=(Map<String, Object>) session.getAttribute("vip");

		String vipid=vip.get("vipid").toString();
		
		//当前页  看哪一页
		String currentPage=request.getParameter("currentPage");
		//如果没有输入页数，则为第一页
		if(currentPage==null   || currentPage.equals("")){
			currentPage="1";
		}
		//过滤条件
		String where=" and a.vipid='"+vipid+"' ";
	
		//	System.out.println("+++++++++++++++++++++++++++++++++"+categoryname);		
		//排序方式
		String orderby="order by a.addressid desc";
		
		
		//页面大小
		int pageSize=5;
		//计算出第几页要看 从第几条开始
		int startIndex=(Integer.parseInt(currentPage)-1)*pageSize;
		
		AddressDao addressDao=new AddressDao();
		//查询出所有的商品
		QueryResult qr= addressDao.listAddressWithOrderby(startIndex, pageSize, where, orderby);
								
		//记录总数
		int totalCount=qr.getTotalCount();
		int totalPage=0;
		//总页数
		int temp =totalCount%pageSize;
		//刚好整除
		if(temp==0){
			totalPage=totalCount/pageSize;
		}else{
			//不能整除；
			totalPage=totalCount/pageSize+1;
		}				
		//把信息放入页面
		request.setAttribute("records",qr.getRecords());
		request.setAttribute("totalCount", qr.getTotalCount());
		//把当前页放入数据
		request.setAttribute("currentPage", currentPage);
		
		//把总页数放入页面
		request.setAttribute("totalPage",totalPage);
	
		//请求转发
		request.getRequestDispatcher("myAddress.jsp").forward(request, response);
	

	}
	//产品的搜查功能
	private void searchProduct(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
	//关键字
		String keyword = request.getParameter("keyword");
		String categoryid = request.getParameter("categoryid");
		
		//判断当前页
		String currentPage=request.getParameter("currentPage");
		if(currentPage==null || currentPage.equals("")){
			currentPage="1";
		}
		
		//设置过滤条件
		String where="";
		if(keyword!=null && keyword.equals("")){
			where=" and a.name like '%"+keyword+"%'";
		}
		if( categoryid!=null && categoryid.equals("") && !categoryid.equals("0")){
			where+=" and a.category="+categoryid;
		}
		//得到位置   你所在的位子
		String categoryname="搜索结果";
		
		//吧当前分类放入页面、
		request.setAttribute("categoryname", categoryname);
		
		//排序方式
		String orderby=" order by a.time desc";
		
		//定义页大小
		int pageSize=4;
		
		//计算从那一条开始
		int startIndex=(Integer.parseInt(currentPage)-1)*pageSize;
		//调用dao
		ProductDao productDao=new ProductDao();
		QueryResult qr = productDao.searchWithWhere(startIndex, pageSize, where, orderby);
		
		//总记录数
		int totalCount=qr.getTotalCount();
		//总页数
		int totalPage=0;
		int temp=totalCount%pageSize;
		//刚好整除
		if(temp==0){
			totalPage=totalCount/pageSize;
		}else{
			totalPage=totalCount/pageSize+1;
		}
		
		//把信息放入页面
		request.setAttribute("records", qr.getRecords());
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("totalPage",totalPage);
		
		request.setAttribute("categoryid", categoryid);
		request.setAttribute("keyword", keyword);
		
		request.getRequestDispatcher("searchlist.jsp").forward(request, response);
		
	}
	//删除一条评论
	private void delComments(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
        
		//删除讨论的ID
		String commentid=request.getParameter("commentid");
		
		//调用DAO
		CommentDao commentDAO=new CommentDao();
		int flag=commentDAO.del(Integer.parseInt(commentid));
		String message="";
		if(flag>0){
			message="<script>alert('操作成功');window.location.href='productServlet?action=mycomment';</script>";
		}else{
			message="<script>alert('操作失败');window.location.href='productServlet?action=mycomment';</script>";
		}
		//把信息放入页面
		request.setAttribute("message", message);

		request.getRequestDispatcher("message.jsp").forward(request, response);

		
	}
	
	//查看评论
	private void mycomments(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//先获取Session 
		HttpSession session = request.getSession();
		Map<String,Object> vip=(Map<String, Object>) session.getAttribute("vip");

		String vipid=vip.get("vip").toString();
		
		//当前页  看哪一页
		String currentPage=request.getParameter("currentPage");
		//如果没有输入页数，则为第一页
		if(currentPage==null   || currentPage.equals("")){
			currentPage="1";
		}
		//过滤条件
		String where=" and a.vipid='"+vipid+"'";
	
		//	System.out.println("+++++++++++++++++++++++++++++++++"+categoryname);		
		//排序方式
		String orderby="order by a.time desc";
		
		
		//页面大小
		int pageSize=5;
		//计算出第几页要看 从第几条开始
		int startIndex=(Integer.parseInt(currentPage)-1)*pageSize;
		
		ProductDao productDao=new ProductDao();
		//查询出所有的商品
		QueryResult qr= productDao.listCommentsWithOrderby(startIndex,pageSize,where,orderby);
		
		//记录总数
		int totalCount=qr.getTotalCount();
		int totalPage=0;
		//总页数
		int temp =totalCount%pageSize;
		//刚好整除
		if(temp==0){
			totalPage=totalCount/pageSize;
		}else{
			//不能整除；
			totalPage=totalCount/pageSize+1;
		}				
		//把信息放入页面
		request.setAttribute("records",qr.getRecords());
		request.setAttribute("totalCount", qr.getTotalCount());
		//把当前页放入数据
		request.setAttribute("currentPage", currentPage);
		
		//把总页数放入页面
		request.setAttribute("totalPage",totalPage);
	
		//请求转发
		request.getRequestDispatcher("myComments.jsp").forward(request, response);
	

	}
	//会员查看自己的订单
	public void myorder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				//得到登陆的session
				 HttpSession session = request.getSession();
				Map<String,Object> vip= (Map<String, Object>) session.getAttribute("vip");
		
				//当前要看哪一页
				String currentPage=request.getParameter("currentPage");
				//如果没有输入页数，那就看第一页
				if(currentPage==null || currentPage.equals("")){
					currentPage="1";
				}				
				//页大小
				int pageSize=2;				
				//计算从哪一条开始看		
				int startIndex=(Integer.parseInt(currentPage)-1)*pageSize;				
				//条件
				String where=" ";				
				//获取订单状态码
				String statuscode=request.getParameter("statuscode");		
				//如果用户提交了订单状态码
				if(statuscode!=null && !statuscode.equals("")){
					//状态
					String status="";
					if(statuscode.equals("1")){
						status="未审核";
						where=" and e.status='"+status+"' and e.vipid='"+vip.get("vipid")+"' ";
					}else if(statuscode.equals("2")){
						status="未付款";
						where=" and e.status='"+status+"' and e.vipid='"+vip.get("vipid")+"' ";
					}else if(statuscode.equals("3")){
						status="未发货";
						where=" and e.status='"+status+"' and e.vipid='"+vip.get("vipid")+"' ";	
					}else if(statuscode.equals("4")){
						status="未确认收货";
						where=" and e.status='"+status+"' and e.vipid='"+vip.get("vipid")+"' ";
					}else if(statuscode.equals("5")){
						status="已完成";
						where=" and e.status='"+status+"' and e.vipid='"+vip.get("vipid")+"'  ";
					}else{
						where=" and e.vipid='"+vip.get("vipid")+"' ";
			            statuscode="";
					}
			
				}
				
				//排序
				String orderby="order by e.maketime desc";
				
				//调用DAO
				ProductDao productDAO=new ProductDao();
				//查询出所有的产品
				QueryResult qr=productDAO.listOrdersWithWhere(startIndex, pageSize, where, orderby);
				
				// 记录总数
				int totalCount=qr.getTotalCount();
				
				
				//总页数
				int totalPage=0;
				int temp=totalCount%pageSize;
				//刚好整除
				if(temp==0){
					totalPage=totalCount/pageSize;
				}else{//不能整除
					totalPage=totalCount/pageSize+1;
				}
						
				
				//把信息放入页面
				request.setAttribute("records", qr.getRecords());
				
				//把记录总数放入页面
				request.setAttribute("totalCount", totalCount);
				
				//把记当前页放入页面
				request.setAttribute("currentPage", currentPage);
				
				//把总页数放入页面
				request.setAttribute("totalPage", totalPage);
				//把状态码放入页面
				request.setAttribute("statuscode", statuscode);
				

		        //跳转页面		
				request.getRequestDispatcher("myOrders.jsp").forward(request, response);
				
			}
	//对商品进行评论
	private void addComment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		//获取数据
		String title = request.getParameter("title");
		String score = request.getParameter("score");
		String content = request.getParameter("content");
		String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		String productid=request.getParameter("productid");
		//为了获取Session 中的vipid
		HttpSession session = request.getSession();
		Map<String,Object> vip = (Map<String, Object>) session.getAttribute("vip");
		
		//封装数据
		Map<String,Object> record= new HashMap<String, Object>();
		record.put("title", title);
		record.put("score", score);
		record.put("content", content);
		record.put("time", time);	
		record.put("productid", productid);
		record.put("vipid", vip.get("vipid"));
		
		CommentDao commentDao=new CommentDao();
		int flag=commentDao.add(record);
		String message="";
		if(flag>0){
			message="<script>alert('评论成功');window.location.href='productServlet?action=showDetail&productid="+productid+"'</script>";
		}else{
			message="<script>alert('评论失败');window.location.href='productServlet?action=showDetail&productid="+productid+"'</script>";

		}
		//将信息存入
		request.setAttribute("message", message);
		//跳转页面
		request.getRequestDispatcher("message.jsp").forward(request, response);
		
	}
	//更新订单状态
	private void updatestatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session=request.getSession();
		Map<String,Object> admin=(Map<String,Object>)session.getAttribute("admin");
		
		OrdersDao ordersDao=new OrdersDao();

		//获取订单编号
		int orderid=Integer.parseInt(request.getParameter("orderid"));
		
		String status="";
		//获取状态码
		String dowhat=request.getParameter("dowhat");
		//提示信息
		String message="";
		if(dowhat.equals("pass")){
		   status="未付款";	
		   message="<script>alert('审核成功');window.location.href='productServlet?action=queryorder&statuscode=1';</script>";
		}else if(dowhat.equals("pay")){
		   status="未发货";
		   message="<script>alert('付款成功');window.location.href='productServlet?action=queryorder&statuscode=2';</script>";
		}else if(dowhat.equals("send")){
		   status="未确认收货";
		   message="<script>alert('发货成功');window.location.href='productServlet?action=queryorder&statuscode=3';</script>";
		}else if(dowhat.equals("receive")){
			status="已完成";
			//认为是前台的操作
			//增加订单的总价
			float sum=Float.parseFloat(ordersDao.getOrderSumWithId(orderid));
		
			VipDao vipDao=new VipDao();
			//要更新的会员对象
			Map<String,Object> record=new HashMap<String, Object>();
		
			if(admin==null){	
				//前台操作
				Map<String,Object> vip = (Map<String, Object>) session.getAttribute("vip");
				int vipid = Integer.parseInt(vip.get("vipid").toString());
			
				//取该用户的原来的积分
				
				float oldscore=Float.parseFloat(vipDao.get(vipid).get("score").toString()) ;
				float newscore=BaseCalculate.add(oldscore, sum);				
				//增加用户的积分
				record.put("score", newscore);			
				//更新Session中的积分
				vip.put("score", newscore);
				//这里注意
				record.put("score",BaseCalculate.add(oldscore, sum));
				record.put("vipid", vipid);
				//record.put("score",);			
				 message="<script>alert('收货成功');window.location.href='productServlet?action=myorder&statuscode=4';</script>";
			}else if(admin!=null){	
				//后台操作
					String vipid=request.getParameter("vipid");
				   
				   //取该用户的原来的积分
				   float oldscore=Float.parseFloat(vipDao.get(Integer.parseInt(vipid)).get("score").toString());
				   
				   //这里要注意//增加用户的积分
					record.put("score", BaseCalculate.add(oldscore, sum));
				   
				   record.put("vipid", vipid);
				   
				   message="<script>alert('收货成功');window.location.href='productServlet?action=queryorder&statuscode=4';</script>";
			}
			vipDao.update(record);   
		}
		
		
		ordersDao.updatestatus(status, orderid);
		
	
		//把信息放入页面
		request.setAttribute("message", message);
		
		request.getRequestDispatcher("message.jsp").forward(request, response);
		
	}
	//后台订单明细
	private void orderDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		//获取订单编号
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		//调用OrdersDao
		OrdersDao ordersDao=new OrdersDao();
		Map<String, Object> record = ordersDao.getOrdersAndVipWithdId(orderid);
		
		//调用订单明细sql语句
		List<Map<String, Object>> records = ordersDao.getOrderdetailAndProductWithdId(orderid);
		
		//订单总价
		String sum = ordersDao.getOrderSumWithId(orderid);
		
		request.setAttribute("sum", sum);
		request.setAttribute("record",record);
		request.setAttribute("records", records);
		
		request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
	
	}

	//管理人员查询订单
	public void queryorder(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		
		//当前要看哪一页
		String currentPage=request.getParameter("currentPage");
		//如果没有输入页数，那就看第一页
		if(currentPage==null || currentPage.equals("")){
			currentPage="1";
		}
		
		//页大小
		int pageSize=2;
		
		//计算从哪一条开始看		
		int startIndex=(Integer.parseInt(currentPage)-1)*pageSize;
		
		//条件
		String where=" ";
		
		//获取订单状态码
		String statuscode=request.getParameter("statuscode");		
		//如果用户提交了订单状态码
		if(statuscode!=null && !statuscode.equals("")){
			//状态
			String status="";
			if(statuscode.equals("1")){
				status="未审核";
				where=" and e.status='"+status+"' ";
			}else if(statuscode.equals("2")){
				status="未付款";
				where=" and e.status='"+status+"' ";
			}else if(statuscode.equals("3")){
				status="未发货";
				where=" and e.status='"+status+"' ";	
			}else if(statuscode.equals("4")){
				status="未确认收货";
				where=" and e.status='"+status+"' ";
			}else if(statuscode.equals("5")){
				status="已完成";
				where=" and e.status='"+status+"' ";
			}else{
				where=" ";
	            statuscode="";
			}
	
		}
		
		//排序
		String orderby="order by e.maketime desc";
		
		//调用DAO
		ProductDao productDAO=new ProductDao();
		//查询出所有的产品
		QueryResult qr=productDAO.listOrdersWithWhere(startIndex, pageSize, where, orderby);
		
		// 记录总数
		int totalCount=qr.getTotalCount();
		
		
		//总页数
		int totalPage=0;
		int temp=totalCount%pageSize;
		//刚好整除
		if(temp==0){
			totalPage=totalCount/pageSize;
		}else{//不能整除
			totalPage=totalCount/pageSize+1;
		}
				
		
		//把信息放入页面
		request.setAttribute("records", qr.getRecords());
		
		//把记录总数放入页面
		request.setAttribute("totalCount", totalCount);
		
		//把记当前页放入页面
		request.setAttribute("currentPage", currentPage);
		
		//把总页数放入页面
		request.setAttribute("totalPage", totalPage);
		//把状态码放入页面
		request.setAttribute("statuscode", statuscode);
		request.setAttribute("pageSize",pageSize);

        //跳转页面		
		request.getRequestDispatcher("listOrders.jsp").forward(request, response);
		
	}

	//生成订单
	public void makeorder(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		//配送地址的ID
		String addressid=request.getParameter("addressid");
		//支付方式
		String payway=request.getParameter("payway");
		
		//下单时间
		String maketime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		//状态
		String status="未审核";
		
		//HttpSession session=request.getSession();
	    //session.getAttribute("vipid");
        int vipid=1;
		
		//拼装订单数据
		Map<String,Object> order=new HashMap<String,Object>();
		order.put("addressid", addressid);
		order.put("payway", payway);
		order.put("maketime", maketime);
		order.put("status", status);
		order.put("vipid", vipid);
		
		//获取Session
		HttpSession session=request.getSession();
		//获取购物车
		List<Map<String,Object>> cart=(List<Map<String,Object>>)session.getAttribute("cart");
				
		
		OrdersDao ordersDAO=new OrdersDao();
		int orderid=ordersDAO.makeOrder(order,cart);
		
		System.out.println("生成的订单ID:"+orderid);
		
		String message="";
		if(orderid>0){
			//下单完成后，建议清空购物车
			session.removeAttribute("cart");
			session.removeAttribute("sum");
			message="<script>alert('下单成功！你的订单号为："+orderid+"');window.location.href='productServlet?action=displayProducts';</script>";
		}else{
			message="<script>alert('下单失败');window.location.href='productServlet?action=displayProducts';</script>";
		}
		//把信息放入页面
		request.setAttribute("message", message);
		 //跳转页面		
		request.getRequestDispatcher("message.jsp").forward(request, response);
		
		
	}
	
	
	//进入订单确认页面
		private void toOrderConfirm(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			//地址id
			String addressid = request.getParameter("address");
			//配送地址的 支付方式
			String payway = request.getParameter("payway");
			
			//获取该id对应配送信息；
			AddressDao addressDao=new AddressDao();
			Map<String, Object> record = addressDao.get(Integer.parseInt(addressid));
		
			//吧信息放入页面
			request.setAttribute("record", record);
			request.setAttribute("payway", payway);
			
			request.getRequestDispatcher("orderConfirm.jsp").forward(request, response);
		}
	
	
		//提交配送地址
		private void saveAddress(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			//获取用户输入的值
			String addressname = request.getParameter("addressname");
			String postcode = request.getParameter("postcode");
			String receiver = request.getParameter("receiver");
			String phone = request.getParameter("phone");
			String bz=request.getParameter("bz");
			//获取session
			/*HttpSession session = request.getSession();
			session.getAttribute("vipid");*/
			
			int vipid=1;
			//组装数据
			Map<String ,Object> record=new HashMap<String, Object>();
			record.put("addressname", addressname);
			record.put("postcode", postcode);
			record.put("receiver", receiver);
			record.put("phone", phone);
			record.put("bz", bz);
			
			record.put("vipid", vipid);
			
			//调用dao
			AddressDao addressDao=new AddressDao();
			int flag=addressDao.add(record);
			String message="";
			if(flag>0){
				message="<script>window.location.href='productServlet?action=toAddress'</script>";
			}else{
				message="<script>window.location.href='productServlet?action=toAddress'</script>";
			}
			request.setAttribute("message", message) ;
			
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
	
	
	
	//进入配送地址页面
	private void toAddress(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		//调用方法
		boolean flag = ValidateLogin.validateVip(request);
		if(flag==true){	 //表示用户登陆； 
		// 获取用户id
		HttpSession session = request.getSession();
		Map<String,Object> vip=(Map<String, Object>) session.getAttribute("vip");
		
		//调用dao
		AddressDao addressDao = new AddressDao();
		
		List<Map<String, Object>> records = addressDao.listByVipid(Integer.parseInt(vip.get("vipid").toString()));
		
		//把records放入页面中
		request.setAttribute("records", records);
		//跳转页面
		request.getRequestDispatcher("address.jsp").forward(request, response);
		}else{
		//用户没有登陆跳转页面
			request.getRequestDispatcher("viplogin.jsp").forward(request, response);
		}
	}
	
	
	//查看购物车
		private void showCart(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
		
		//获取Session
		HttpSession session = request.getSession();
		//获取到车子  信息已经到session中了	
 		//List<Map<String,Object>> cart=(List<Map<String, Object>>) session.getAttribute("cart");
		//获取总价
 		 if(session.getAttribute("sum")==null){
 			 session.setAttribute("sum", 0);
 		 } 
 		request.getRequestDispatcher("showCart.jsp").forward(request, response);
		
		}
	
	
	//将产品 从购物车中删除
	private void delCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
	//获取session
		HttpSession session = request.getSession();
		//获取要删除产品的id
		String productid = request.getParameter("productid");
	//获取购物车	
		List<Map<String,Object>> cart = (List<Map<String, Object>>) session.getAttribute("cart");
	//获取已经购买产品的总价
		float sum = (Float) session.getAttribute("sum");
		
	 //从购物车中删除该产品
		for(int i=0;i<cart.size();i++){
			Map<String, Object> temp = cart.get(i);			
		//找到了要删除的产品的id
			if(temp.get("productid").equals("productid")){
				//更新产品总价
				sum=BaseCalculate.substract(sum, Float.parseFloat(temp.get("totalprice").toString()));
			//更新session中的总价
				session.setAttribute("sum",sum);
				
				//删除该产品
				cart.remove(temp);
				break;
				//终止循环
			}
		}
		
		
		//跳转页面
		//request.getRequestDispatcher("showCart.jsp").forward(request, response);

		//解决刷新重复提交
		response.sendRedirect("showCart.jsp");
	}
	
	//购物车 方法
	private void addCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		String productid=request.getParameter("productid");
		 int buycount=Integer.parseInt(request.getParameter("buycount"));
		//查询数据库 ，获取该产品的信息
		ProductDao productDao=new ProductDao();
		Map<String,Object> record=productDao.get(Integer.parseInt(productid));
	
		
		//获取session, 是指用于多个页面实现数据共享 技术，典型应用场景 1.购物车 2.判断用户是否登陆
		HttpSession session = request.getSession();
		
		List<Map<String ,Object>> cart=null;
		
		//购买第一次  产品 没有购物车，生产购物车 ;
		if(session.getAttribute("cart")==null){
		//创建一个用于描述购物车的List;  不能重复创建购物车；
		 cart=new ArrayList<Map<String,Object>>();			
		 //设置商品数量（增加）;
		 record.put("buycount", buycount);
		 //总价
		// record.put("totalprice", buycount*Float.parseFloat(record.get("myprice").toString()));
	record.put("totalprice",  BaseCalculate.round(buycount*Float.parseFloat(record.get("myprice").toString()), 1));

		 //把产品放入车子；
		 cart.add(record);
		 
		 
		}else{
			//已经创建购物车；
			 cart = (List<Map<String, Object>>) session.getAttribute("cart");
			//拿到之前的车子
			//判断该次购买的产品是否在车中，默认不在车里
			 boolean inCart=false;
			
			 //循环车子 判断当前购买的产品是否已经购买过，重复购买
			for(int i=0;i<cart.size();i++){
				//获取每一条产品 
				Map<String, Object> temprecord=cart.get(i);
				if(temprecord.get("productid").equals(productid)){
					//取出之前购买的数量,加上本次的购买数量
					temprecord.put("buycount", Integer.parseInt(temprecord.get("buycount").toString())+buycount);			
					//重新计算总价
					temprecord.put("totalprice", BaseCalculate.round(BaseCalculate.add(Float.parseFloat(temprecord.get("totalprice").toString()), buycount*Float.parseFloat(record.get("myprice").toString())), 1));
					//将更改的记录重新放入Cart(更改)
					//cart.add(i, temprecord);	
					inCart=true;
					break;
				}
				
			}
			if(inCart==false){
				//说明该产品不在车中， 重新加入该产品
				//增加数量列
				record.put("buycount", buycount);
				//增加总价列
				record.put("totalprice",  BaseCalculate.round(buycount*Float.parseFloat(record.get("myprice").toString()), 1));
				System.out.println("===="+record);
				//把商品放入购物车
				cart.add(record);
				
				
			}
		
		}
		//购物车商品总价
		float sum=0;
		
		//重新循环购物车  统计总价
		for(int i=0;i<cart.size();i++){
			Map<String, Object> temp = cart.get(i);
			//当前商品的总价
			sum=BaseCalculate.round(BaseCalculate.add(sum,Float.parseFloat(temp.get("totalprice").toString())), 1);	
		}
		//把总价放到session中
		session.setAttribute("sum", sum);
		
		
		//将购物车放入session
		session.setAttribute("cart",cart);
		//重定向页面
		response.sendRedirect("showCart.jsp");
		
		//	request.getRequestDispatcher("showCart.jsp").forward(request, response);
	
	}
	
	
	//商品详情页面
		private void showDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
			//获取到传来的商品id	
			String productid = request.getParameter("productid");
			
			//调用dao
			ProductDao productDao=new ProductDao();
			Map<String, Object> record = productDao.get(Integer.parseInt(productid));
			
			//以前的浏览量
			int oldhit=Integer.parseInt(record.get("hit").toString());
			//最新的浏览量
			int newhit=oldhit+1;
			//把新的浏览量重新放入record
			record.put("hit", newhit);
			//更新改产品的浏览量
			productDao.update(record);
			
			//将record 传入页面
			request.setAttribute("record",record);
			request.getRequestDispatcher("showDetail.jsp").forward(request, response);
		
	}
		//前台产品展示页面
		public void displayProducts(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {	
			//分类的id
			String categoryid=request.getParameter("categoryid");
			
			//当前页  看哪一页
			String currentPage=request.getParameter("currentPage");
			//如果没有输入页数，则为第一页
			if(currentPage==null   || currentPage.equals("")){
				currentPage="1";
			}
			//过滤条件
			String where=" ";
			String categoryname="全部商品";
		//	System.out.println("11111111111111111111111111111111111111111111111111111"+categoryid);
			if(categoryid!=null && !categoryid.equals("")){
				where=" and a.categoryid='"+categoryid+"' ";
				
				CategoryDao categoryDAO=new CategoryDao();
				Map<String,Object> temp=categoryDAO.get(Integer.parseInt(categoryid));
				categoryname=temp.get("name").toString();
			}
			
			//把当前分类的名字放入页面
			request.setAttribute("categoryname", categoryname);
			
		
			System.out.println("+++++++++++++++++++++++++++++++++"+categoryname);
			
			//排序方式
			String orderby="order by time desc";
			
			//按谁排序
			String  orderfield=request.getParameter("orderfield");
			//如何排序
			String ordervalue=request.getParameter("ordervalue");
			
			//判断是否有查询条件
			if(orderfield!=null && !orderfield.equals("") && ordervalue!=null  && !ordervalue.equals("")){
				orderby ="order by "+orderfield+" "+ordervalue;
			}
			//页面大小
			int pageSize=5;
			//计算出第几页要看 从第几条开始
			int startIndex=(Integer.parseInt(currentPage)-1)*pageSize;
			
			ProductDao productDao=new ProductDao();
			//查询出所有的商品
			QueryResult qr= productDao.listWithOrderby(startIndex,pageSize,where,orderby);
			
			//记录总数
			int totalCount=qr.getTotalCount();
			int totalPage=0;
			//总页数
			int temp =totalCount%pageSize;
			//刚好整除
			if(temp==0){
				totalPage=totalCount/pageSize;
			}else{
				//不能整除；
				totalPage=totalCount/pageSize+1;
			}				
			//把信息放入页面
			request.setAttribute("records",qr.getRecords());
			request.setAttribute("totalCount", qr.getTotalCount());
			//把当前页放入数据
			request.setAttribute("currentPage", currentPage);
			
			//把总页数放入页面
			request.setAttribute("totalPage",totalPage);
			request.setAttribute("orderfield", orderfield);
			request.setAttribute("ordervalue", ordervalue);
			//把categoryid 放入页面
			request.setAttribute("categoryid",categoryid);
			
			//请求转发
			request.getRequestDispatcher("displayProducts.jsp").forward(request, response);
		}
		
		
	//文件上传
	private void upload(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		try {
			String curDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			
			//临时文件上传目录 （获取网站的更目录的绝对路径）
			String tempFileUploadDir2=this.getServletConfig().getServletContext().getRealPath("/")+"temp/"+curDate+"/";
			//文件上传目录  将文件上传到网站的目录里面
			String fileUploadDir2=this.getServletConfig().getServletContext().getRealPath("/")+"file/"+curDate+"/";
			
			File tempFileUploadDir =new File(tempFileUploadDir2);
			File fileUploadDir =new File(fileUploadDir2);
			//如果临时目录不存在 则创建
			if(!tempFileUploadDir.exists()){
				tempFileUploadDir.mkdirs();
			}
			//如果上传目录不存在，则创建
			if(!fileUploadDir.exists()){
				fileUploadDir.mkdirs();
			}
			
			//创建一个文件磁盘 条目工厂
			DiskFileItemFactory factory=new DiskFileItemFactory();
			//设置工厂的一些属性
			factory.setSizeThreshold(9999999);
			//设置上传的缓存目录
			factory.setRepository(tempFileUploadDir);
			
			//创建一个新的文件上传处理器
			ServletFileUpload upload=new ServletFileUpload(factory);
			//设置上传文件的最大值  限制文件大小不能大于2MB;
			upload.setSizeMax(2048000);
			//获得所有的上传数据  （文件和表单字段）
			List items=upload.parseRequest(request);
			
			System.out.println("items size :"+items.size());
			//提示信息
			String message="";
			//将items 转成迭代器
			Iterator iter=items.iterator();
			while(iter.hasNext()){
				//某一个表单数据
				FileItem item=(FileItem) iter.next();
				//如果不是表单数据  就上传
				if(!item.isFormField()){
					//如果是IE6 这里得到的是要上传文件的路径 d:\abc.txt
					//如果是IE8 这里得到的是要上传文件的名字  abc.text
					String filename=item.getName();
					System.out.println("filename  :"+filename);
					
					//取文件的后缀
					String  suffix=filename.substring(filename.lastIndexOf("."));
					System.out.println("取出的文件后缀为："+suffix);
					//获取到一个新的文件名字
					SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
					//新生产的文件名
					String newfilename=sdf.format(new Date())+suffix;
					
					//获取到新的  上传后的 文件的绝对位置  如:d\file\201511014.txt
					String newposition=fileUploadDir2+newfilename;
					//将文件写入磁盘
					item.write(new File(newposition));
					
					message="上传成功";
					request.setAttribute("message",message);
					
					//上传后文件的相对路径			
					String filepath="file/"+curDate+"/"+newfilename;
					//把上传后的文件相对路径放入页面
					request.setAttribute("filepath", filepath);
					
					//跳转页面
					request.getRequestDispatcher("upload.jsp").forward(request, response);
				}
			}
		} catch (Exception e) { 
			
			e.printStackTrace();
		}
	}
	//保存修改
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     
			//1、获取数据
			String name=request.getParameter("name");
			String myprice=request.getParameter("myprice");
			String marketprice=request.getParameter("marketprice");
			String storecount=request.getParameter("storecount");
			String hit=request.getParameter("hit");
			String time=request.getParameter("time");
			String photo=request.getParameter("photo");
			String categoryid=request.getParameter("categoryid");
			String content=request.getParameter("content");
			String productid = request.getParameter("productid");
			
			//2、组装数据
			Map<String,Object> record=new HashMap<String,Object>();
			record.put("name", name);
			record.put("myprice", myprice);
			record.put("marketprice", marketprice);
			record.put("storecount", storecount);
			record.put("hit", hit);
			record.put("time", time);
			record.put("photo", photo);
			record.put("categoryid", categoryid);
			record.put("content", content);
			
			record.put("productid",productid);
			//3、调用DAO
			ProductDao productDAO=new ProductDao();
			int flag=productDAO.update(record);
			String message="";
			if(flag>0){
				
				message="<script>alert('更新成功');window.location.href='productServlet?action=list'</script>";
			}else{
				message="<script>alert('更新失败');window.location.href='productServlet?action=list'</script>";
			}
			//把信息放入页面
			request.setAttribute("message", message);
			//4、跳转页面		
			//list(request,response);
		request.getRequestDispatcher("message.jsp").forward(request, response);
		
	}
	//进入修改页面
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productid = request.getParameter("productid");
		
		ProductDao productDao=new ProductDao();
		Map<String,Object> record=productDao.get(Integer.parseInt(productid));
		
		request.setAttribute("record", record);
		
		//调用dao 查询分类
		CategoryDao categoryDao=new CategoryDao();
		List<Map<String,Object>> records=categoryDao.list();
		request.setAttribute("records", records);		
		request.getRequestDispatcher("editProduct.jsp").forward(request, response);
		
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//删除产品的ID
				String productid=request.getParameter("productid");
				
				//调用DAO
				ProductDao productDAO=new ProductDao();
				int flag=productDAO.delete(Integer.parseInt(productid));
				String message="";
				if(flag>0){
					//System.out.println("添加成功");
					message="<script>alert('删除成功');window.location.href='productServlet?action=list'</script>";
				}else{
					message="<script>alert('删除失败');window.location.href='productServlet?action=list'</script>";
				}
				//把信息放入页面
				request.setAttribute("message", message);

		        //跳转页面		
				//list(request,response);
				request.getRequestDispatcher("message.jsp").forward(request, response);
	}
	//产品列表
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//当前页  看哪一页
		String currentPage=request.getParameter("currentPage");
		//如果没有输入页数，则为第一页
		if(currentPage==null   || currentPage.equals("")){
			currentPage="1";
		}
		//页面大小
		int pageSize=2;
		//计算出第几页要看 从第几条开始
		int startIndex=(Integer.parseInt(currentPage)-1)*pageSize;
		
		ProductDao productDao=new ProductDao();
		//查询出所有的商品
		QueryResult qr= productDao.list(startIndex,pageSize);
		
		//记录总数
		int totalCount=qr.getTotalCount();
		int totalPage=0;
		//总页数
		int temp =totalCount%pageSize;
		//刚好整除
		if(temp==0){
			totalPage=totalCount/pageSize;
		}else{
			//不能整除；
			totalPage=totalCount/pageSize+1;
		}
		
		
		
		//把信息放入页面
		request.setAttribute("records",qr.getRecords());
		request.setAttribute("totalCount", qr.getTotalCount());
		//把当前页放入数据
		request.setAttribute("currentPage", currentPage);
		
		//把总页数放入页面
		request.setAttribute("totalPage",totalPage);
		
		//请求转发
		request.getRequestDispatcher("listProduct.jsp").forward(request, response);
	}
	
	//进入发布页面
	public void publishUI(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
	
		//调用dao
		CategoryDao categoryDao=new CategoryDao();
		List<Map<String,Object>> records=categoryDao.list();
		
		request.setAttribute("records",records);
		
		request.getRequestDispatcher("addProduct.jsp").forward(request, response);
		
	}
	
	//发布商品
	public void publish(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
           
		//1、获取数据
		String name=request.getParameter("name");
		String myprice=request.getParameter("myprice");
		String marketprice=request.getParameter("marketprice");
		String storecount=request.getParameter("storecount");
		String hit=request.getParameter("hit");
		String time=request.getParameter("time");
		String photo=request.getParameter("photo");
		String categoryid=request.getParameter("categoryid");
		String content=request.getParameter("content");
		
		//2、组装数据
		Map<String,Object> record=new HashMap<String,Object>();
		record.put("name", name);
		record.put("myprice", myprice);
		record.put("marketprice", marketprice);
		record.put("storecount", storecount);
		record.put("hit", hit);
		record.put("time", time);
		record.put("photo", photo);
		record.put("categoryid", categoryid);
		record.put("content", content);
		
		//3、调用DAO
		ProductDao productDAO=new ProductDao();
		int flag=productDAO.add(record);
		String message="";
		if(flag>0){
			System.out.println("添加成功");
			message="<script>alert('操作成功');window.location.href='productServlet?action=list'</script>";
		}else{
			message="<script>alert('操作失败');window.location.href='productServlet?action=list'</script>";
		}
		//把信息放入页面
		request.setAttribute("message", message);
		//4、跳转页面
		request.getRequestDispatcher("message.jsp").forward(request, response);
	//	list(request,response);
		
    }
	

}
