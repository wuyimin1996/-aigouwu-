package com.wu.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CheckCodeServlet extends HttpServlet {

	private static final long serialVersionUID = -8112015860301637701L;
	
	private Font mFont=new Font("Times New Roman", Font.PLAIN,18);//设置字体
	 //static String AuthCode="";// 处理post
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	     throws ServletException,IOException {// 取得一个1000-9999的随机数
	     HttpSession session=request.getSession();//request.getSession(false)当参数为false时，有可能不创建session //session.setAttribute("getImg",s);
	     //设置Servlet返回的是图形的内容
	     response.setContentType("image/gif");
	     //防止缓存
	     response.setHeader("Pragma","No-cache");
	     response.setHeader("Cache-Control","no-cache");
	     response.setDateHeader("Expires", 0);
	     
	     //要生成的验证码图形的宽度和高度（默认）
	     int width=60;
	     int height=20;
	     
	     if(request.getParameter("width")!=null && !request.getParameter("width").equals(""))
	      width = Integer.parseInt(request.getParameter("width"));
	     if(request.getParameter("height")!=null && !request.getParameter("height").equals(""))
	      height = Integer.parseInt(request.getParameter("height"));
	     
	     //产生Servlet的输出流
	     ServletOutputStream out=response.getOutputStream();
	     
	     BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); //设置图片大小的
	     Graphics gra=image.getGraphics();
	     Random random=new Random();gra.setColor(getRandColor(200,250));    //设置背景色
	     gra.fillRect(0,0,width,height);gra.setColor(Color.black); //设置字体色
	     gra.setFont(mFont);// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
	     gra.setColor(getRandColor(160,200));
	     for (int i=0;i<155;i++)
	     {
	      int x = random.nextInt(width);
	      int y = random.nextInt(height);
	      int xl = random.nextInt(12);
	      int yl = random.nextInt(12);gra.drawLine(x,y,x+xl,y+yl);
	     }
	     // 取随机产生的认证码(4位数字)
	     String sRand="";
	     for (int i=0;i<4;i++){
	         String rand=String.valueOf(random.nextInt(10));
	         sRand+=rand;
	         // 将认证码显示到图象中
	         gra.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
	         //调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
	         gra.drawString(rand,13*i+6,16);
	     }
	         //System.out.println("showimage="+sRand);
	         //session.setAttribute("getImg",sRand);
	         //将验证码放入session
	         session.setAttribute("checkcode",sRand);
	         //AuthCode = sRand;
	         //session.putValue("AuthCode",sRand);
	         JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(out);
	         encoder.encode(image);//out.flush();
	 }
	
	
	  static Color getRandColor(int fc,int bc){//给定范围获得随机颜色
	          Random random = new Random();
	          if(fc>255) fc=255;
	          if(bc>255) bc=255;
	          int r=fc+random.nextInt(bc-fc);
	          int g=fc+random.nextInt(bc-fc);
	          int b=fc+random.nextInt(bc-fc);
	          return new Color(r,g,b);
	   }
	  
	  
	   static public String getAuthCode(HttpSession session){//返回验证
	        return (String)session.getAttribute("checkcode");
	   }


	public void init() throws ServletException {
		// Put your code here
	}

}

