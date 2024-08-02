package controller;

import java.io.IOException;

import entity.vo.MessageModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

@SuppressWarnings("serial")
@WebServlet("/login")
public class UserServlet extends HttpServlet{
	//实例化UserService
	private UserService userService = new UserService();
	/**
	 * 
	 * 	controller层
		1.接收客户端请求（调用service层，返回结果）
		2.调用service层的方法，返回消息模型对象；
		3.判断消息模型状态码
			如果为失败
				将消息模型对象设置到request作用域，请求转发跳转会登录页面login.jsp
			如果成功
				将消息模型对象中的用户信息设置到session作用域，重定向跳转到首页index.jsp
		4.请求转发跳转到登录页面
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{
		// receive client's request
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
//		String hashedPassword = PasswordUtil.hashPassword(password, PasswordUtil.generateSalt());
		//调用service层方法，返回消息模型对象
		MessageModel messageModel = userService.userLogin(userName,password);
			
		//判断消息模型状态码
		if(messageModel.getCode() ==1){ // success
			req.getSession().setAttribute("user", messageModel.getObject());
			resp.sendRedirect("index.jsp");
		}else { //failed
			BaseServlet.forwardToUrl(req, resp, messageModel, "login.jsp");
//			req.setAttribute("messageModel", messageModel);
//			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}

}
