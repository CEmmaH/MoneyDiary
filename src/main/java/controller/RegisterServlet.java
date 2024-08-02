package controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.User;
import entity.vo.MessageModel;
import generator.Generator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import util.PasswordUtil;
import util.StringUtil;


@SuppressWarnings("serial")
@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	private static final Logger logger = LogManager.getLogger(RegisterServlet.class);
	UserService userService = new UserService();
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String action = request.getParameter("action");
		String userName = request.getParameter("userName");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		MessageModel messageModel  = new MessageModel();
		
		 if (action == null) {
			 logger.error("Invalid action!");
	         messageModel.setMessage("Invalid action!");
	         messageModel.setCode(0);
	         BaseServlet.forwardToRegister(request, response, messageModel);
	         return;
	     }
		
		if(action.equals("passwordGenerator")) {
			logger.info("Generating password...");
			Generator generator = new Generator();
			password = generator.generatePassword();
			logger.info("Generated password: {}", password);
			User user = new User(userName,firstName,lastName, email, password);
			messageModel.setObject(user);
			messageModel.setMessage("");
			BaseServlet.forwardToRegister(request, response, messageModel);
	        return;
		}
		String hashedPassword = PasswordUtil.hashPassword(password, PasswordUtil.generateSalt());		
		User user = new User(userName,firstName,lastName, email, hashedPassword);
		messageModel.setObject(user);
		
		if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(firstName) || StringUtil.isEmpty(lastName) || StringUtil.isEmpty(email) 
				|| StringUtil.isEmpty(password)) {
			messageModel.setMessage("UserName, FistName, LastName, email and password can't be empty!");
			messageModel.setCode(0);
			BaseServlet.forwardToRegister(request, response, messageModel);
			return;
		}
		messageModel = userService.userRegister(user);
		if(messageModel.getCode()==0) {
			logger.warn("User registration failed.");
			user.setHashed_Password(password);
//			messageModel.setObject(user);
			BaseServlet.forwardToRegister(request, response, messageModel);
		}else {
			logger.info("User registration successful.");
			request.getSession().setAttribute("messageModel", messageModel);				
			response.sendRedirect("login.jsp");
		}		
	}
}
