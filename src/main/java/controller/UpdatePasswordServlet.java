package controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.User;
import entity.vo.MessageModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;

@SuppressWarnings("serial")
@WebServlet("/updatePasswordServlet")
public class UpdatePasswordServlet extends HttpServlet{
	
	private static final Logger logger = LogManager.getLogger(RegisterServlet.class);
	UserService userService = new UserService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		HttpSession session = request.getSession(false);
		MessageModel messageModel = new MessageModel();
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");

		if(session == null) {
			logger.error("Invalid Session.");
			messageModel.setMessage("Session expired, please log in.");
			messageModel.setCode(0);
			request.setAttribute("activeTab", "tab_Update_passwd");
			BaseServlet.forwardToUrl(request, response, messageModel, "index.jsp");
		}else {			
			User user = (User)session.getAttribute("user");
			messageModel.setObject(user);
			messageModel = userService.updateUserPassword(user.getId(), oldPassword, newPassword);
			request.setAttribute("activeTab", "tab_Update_passwd");
			request.getSession().setAttribute("activeTab", "tab_Update_passwd");
			BaseServlet.redirectToUrl(request, response, user, messageModel, "index.jsp");
		}
	}
}
