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
	private UserService userService = new UserService();
	/**
	 * 
	 * Controller layer
	 * 1. Receives client requests (calls the service layer and returns results)
	 * 2. Calls methods in the service layer and returns a message model object
	 * 3. Checks the status code of the message model
	 *    If it indicates failure
	 *       Sets the message model object into the request scope and forwards the request to the login page (login.jsp)
	 *    If successful
	 *       Sets the user information from the message model object into the session scope and redirects to the homepage (index.jsp)
	 * 4. Forwards the request to the login page
	 */

	protected void service(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{
		// receive client's request
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
//		String hashedPassword = PasswordUtil.hashPassword(password, PasswordUtil.generateSalt());

		MessageModel messageModel = userService.userLogin(userName,password);
			
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
