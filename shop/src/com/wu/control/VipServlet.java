package com.wu.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wu.dao.VipDao;
import com.wu.util.MD5;
import com.wu.util.MailUtil;

public class VipServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//转码
		request.setCharacterEncoding("utf-8");
		
		//区别方法
		String action = request.getParameter("action");
			
		if(action.equals("login")){
			login(request,response);//登陆
		}else if(action.equals("logout")){
			logout(request,response);//退出
		}else if(action.equals("register")){
			register(request,response);//注册
		}else if(action.equals("toInputAnswer")){
			//进入输入答案页面
			toInputAnswer(request,response);
		}else if(action.equals("answer")){
			answer(request,response);//重置密码
		}else if(action.equals("edit")){
			edit(request,response);//进入修改信息界面
		}else if(action.equals("updatepwd")){
			updatepwd(request,response);//进入修改密码界面
		}else if(action.equals("update")){
			update(request,response); //修改个人信息
		}else if(action.equals("validateUsername")){
			//用户响应ajax验证用户的提交（用于验证用户名）
			validateUsername(request,response);
		}
		
		
		
	}
	//用户响应ajax验证用户的提交（用于验证用户名）
	private void validateUsername(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		//获取name
		String username = request.getParameter("username");
		VipDao vipDao=new VipDao();
		Map<String, Object> vip = vipDao.getByusername(username);
		//得到writer 对象
		PrintWriter out=response.getWriter();
		//判断
		if(vip==null){
			//该用户名不存在  可以注册
			out.print("1");
		}else{
			//用户名存在
			out.print("2"); //不可以注册
		}
		
	}


	//修改个人信息
	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
			//
			HttpSession session = request.getSession();
			Map<String,Object> vip = (Map<String, Object>) session.getAttribute("vip");
			String vipid = vip.get("vipid").toString();
			
			//获取需要修改的项
			String sex = request.getParameter("sex");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String question = request.getParameter("question");
			String answer = request.getParameter("answer");
		
			//组装数据
			Map<String,Object> record=new HashMap<String, Object>();
			record.put("sex", sex);
			record.put("email", email);
			record.put("phone", phone);
			record.put("question", question);
			record.put("answer", answer);
			//获取vipid 为了调用修改方法
			record.put("vipid", vipid);
			
			VipDao vipDao=new VipDao();
			int flag=vipDao.update(record );
			String message="";
			if(flag>0){
				message="<script> alert('操作成功'); window.location.href='vipServlet?action=edit'</script>";
			}else{
				message="<script> alert('操作成功'); window.location.href='vipServlet?action=edit'</script>";
			}
			request.setAttribute("message",message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
			
	}


	//会员密码修改方法
	private void updatepwd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		//现获取session
		HttpSession session = request.getSession();
		Map<String,Object> vip = (Map<String, Object>) session.getAttribute("vip");
		String vipid = vip.get("vipid").toString();
		
		String  username=vip.get("username").toString();
		
		//获取需要修改的项
		String oldpwd = request.getParameter("oldpwd");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");

			//先判断原始密码是否正确
		VipDao vipDao=new VipDao();
		String message="";
		if(password1.equals(password2)){
			if(vipDao.validateVip(username, MD5.md5(oldpwd))!=null){
				//说明原始密码正确
				//组装数据
				Map<String,Object> record=new HashMap<String, Object>();
				
				record.put("password",MD5.md5(password1));
				//获取vipid 为了调用修改方法
				record.put("vipid", vipid);
				
				
				int flag=vipDao.update(record);
			
				if(flag>0){
					message="<script> alert('操作成功'); window.location.href='vipServlet?action=edit'</script>";
				}else{
					message="<script> alert('操作成功'); window.location.href='vipServlet?action=edit'</script>";
				}
			}else{
				//说明原始密码不正确
				message="<script> alert('原始密码不正确'); window.location.href='editpwd.jsp'</script>";

			}
			
		}else{
			message="<script> alert('2次密码输入不正确'); window.location.href='editpwd.jsp'</script>";
		}
		
		
		
		request.setAttribute("message",message);
		request.getRequestDispatcher("message.jsp").forward(request, response);
		
		
	}

	//进入修改信息界面
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			//从session 中获取vipid 更加安全
			HttpSession session = request.getSession();
			Map<String,Object> vip = (Map<String, Object>) session.getAttribute("vip");
			
			//获取vipID
			 String username = vip.get("username").toString();
			 //调用dao
			 VipDao vipDao=new VipDao();
			 Map<String, Object> record = vipDao.getByusername(username);
			 request.setAttribute("record", record);
			 
			 //跳转页面
			 request.getRequestDispatcher("editvip.jsp").forward(request, response);
			 
	
	}	


	//修改密码
	private void answer(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//接收用户名
		String username = request.getParameter("username");
		
		//
		String question = request.getParameter("question");
		
		String answer=request.getParameter("answer");
		
		VipDao vipDao=new VipDao();
		Map<String,Object> record=vipDao.validateAnswer(username, question, answer);		
		String message="";
		System.out.println("程序到这里了+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		//System.out.println(record.get("password"));
		//说明验证信息通过
		if(record!=null){
				//重置密码为000000
				Map<String,Object> user=new HashMap<String,Object>();
				user.put("password", MD5.md5("wuyimin"));
				user.put("vipid", record.get("vipid"));
				int isSuccess=vipDao.update(user);
				
				if(isSuccess>0){
					 message="<script>alert('恭喜你，你的密码已经被重围为wuyimin，请及时登录修改！');window.location.href='productServlet?action=displayProducts';</script>";		
				}else{
					message="<script>alert('重围失败，请联系管理员！');window.location.href='productServlet?action=displayProducts';</script>";		
				}							
		}else{
			    message="<script>alert('你输入的答案不正确');window.location.href='productServlet?action=displayProducts';</script>";				
			
		}
		
		request.setAttribute("message",message);
		request.getRequestDispatcher("message.jsp").forward(request, response);
	}


	private void toInputAnswer(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException{
	//接收用户名
		String username = request.getParameter("username");
	//Vip
		VipDao vipDao=new VipDao();
		Map<String, Object> record = vipDao.getByusername(username);
		
		//说明你的用户不存在
		if(record==null){
			String message="<script>alert('该用户不存在');window.location.href='productServlet?action=displayProducts';</script>";
			request.setAttribute("message",message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}else{
			request.setAttribute("record",record);
			request.getRequestDispatcher("inputAnswer.jsp").forward(request, response);
		}
	}


	//注册
		private void register(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException{
			
			//获取前台输入的值
			String username = request.getParameter("username");
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			String sex = request.getParameter("sex");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String question = request.getParameter("question");
			String answer = request.getParameter("answer");
			
			String message="";
			//组装数据	
			Map<String,Object> record=new HashMap<String, Object>();
			record.put("username", username);
			record.put("password",MD5.md5(password1));
			record.put("sex",sex);
			record.put("email",email);
			record.put("phone",phone);
			record.put("score",0);
			record.put("question",question);
			record.put("answer", answer);
			record.put("lastlogintime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			//获取Session 保证在跳转页面的时候 数据还在
			//HttpSession session = request.getSession();
			//不能为空
			boolean issuccess=false;
			if(password1!=null && !password1.equals("") && password2!=null && !password2.equals("") && answer!=null && !answer.equals("")){
					//判断两次的密码是否一致
					if(password1.equals(password2)){
						
						
						    //判断该用户名是否已经存在
						    VipDao vipDAO=new VipDao();
						    Map<String,Object> user=vipDAO.getByusername(username);
						    //说明可以注册
						    if(user==null){
							    				
									//3、调用DAO				
									int flag=vipDAO.addVip(record);
									
									if(flag>0){
										issuccess=true;
										//注册成功 发送邮件
										MailUtil mailUtil=new MailUtil();
										mailUtil.sendMail("smtp.163.com", "wuyiminjiande@163.com", "wuyiminjiande", "wuyimin123456", record.get("email").toString(), "恭喜您成功注册应答网", "GB2312", "这是发送的提示邮件，请不要回复");
										
										
										message="<script>alert('注册成功');window.location.href='productServlet?action=displayProducts';</script>";
									}else{
										message="<script>alert('注册失败');</script>";
									}
						    	 	
						    	
						    }else{
						    	message="<script>alert('该用户已经存在');</script>";
						    	
						    }
							
						
					}else{
						message="<script>alert('两次密码不一致');</script>";
					}
				
			}else{
				message="<script>alert('请正确填写信息');</script>";
			}
			
			
			request.setAttribute("message", message);
			//吧用户填写的信息放入页面
			if(issuccess){
				request.getRequestDispatcher("message.jsp").forward(request, response);
			}else{
				//把用户填写的信息放入页面

				request.setAttribute("record",record);
				//跳转页面
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
			//页面跳转
			//request.getRequestDispatcher("message.jsp").forward(request, response);
		}	

		
	//退出登陆
	private void logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//获取Session
		HttpSession session = request.getSession();
		//注销 
		session.invalidate();
		
		String message="";
		message="<script>alert('注销成功 ！');window.location.href='productServlet?action=displayProducts';</script>";
		
		request.setAttribute("message",message);
		
		request.getRequestDispatcher("message.jsp").forward(request, response);		
	}	


	//会员登录
		public void login(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			//用户提交的验证码
			String checkcode=request.getParameter("checkcode");
			String message="";
			
			HttpSession session=request.getSession();
			//服务器保存的验证码
			String realcheckcode=session.getAttribute("checkcode").toString();
			//验证码正确
			if(checkcode.equals(realcheckcode)){
					VipDao vipDAO=new VipDao();
					Map<String,Object> vip=vipDAO.validateVip(username, MD5.md5(password));
					
					if(vip!=null){
						
						
						//更新上一次的登录时间
						Map<String,Object> record=new HashMap<String,Object>();
						record.put("lastlogintime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						//record.put("score", 999);
						
						record.put("vipid", vip.get("vipid"));
			
						vipDAO.update(record);
						
						
						//把该会员信息放入Session	
						session.setAttribute("vip", vip);
						
						//如果用户是买了东西之后才被转向登录的
						if(session.getAttribute("cart")!=null){
							message="<script>alert('登录成功');window.location.href='productServlet?action=toAddress';</script>";
						}else{				
							message="<script>alert('登录成功');window.location.href='productServlet?action=displayProducts';</script>";
						}
						
						
						
					}else{
						message="<script>alert('登录失败');window.location.href='productServlet?action=displayProducts';</script>";
					}		
				
			}else{		
				message="<script>alert('验证码错误');window.location.href='productServlet?action=displayProducts';</script>";		
			}
			
			//把信息放入页面
			request.setAttribute("message", message);

	        //跳转页面		
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
}
