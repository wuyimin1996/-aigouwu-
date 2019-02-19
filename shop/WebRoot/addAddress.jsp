<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 


      
            <form action="" method="post">
               <table border="1" bordercolor="blue" cellspacing="0" cellpadding="0" style="border-collapse:collapse;width:60%;">
                  <tr>
                      <td>收货地址：</td>
                      <td><input type="text" name="addressname" id="addressname"></td>
                  </tr>
                  <tr>
                      <td>邮编：</td>
                      <td><input type="text" name="postcode" id="postcode"></td>
                  </tr>
                  <tr>
                      <td>收货人：</td>
                      <td><input type="text" name="receiver" id="receiver"></td>
                  </tr>
                  <tr>
                      <td>联系电话：</td>
                      <td><input type="text" name="phone" id="phone"></td>
                  </tr>
                  <tr>
                      <td>备注：</td>
                      <td><input type="text" name="bz" id="bz"></td>
                  </tr>
                  <tr>
                      <td colspan="2"><input type="button" value="确定" onclick="postAddress();"></td>
                  </tr>            
               </table>
               </form>			
				  

 
     
 