package controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.Account;
import entity.User;
import entity.vo.MessageModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.AccountService;


@SuppressWarnings("serial")
@WebServlet("/addAccount")
public class AddAccountServlet extends HttpServlet{
	
	private static final Logger logger = LogManager.getLogger(RegisterServlet.class);
	AccountService accountService = new AccountService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		String name = request.getParameter("name");
		HttpSession session = request.getSession(false);
		MessageModel messageModel = new MessageModel();
		if(session == null) {
			logger.error("Invalid session.");
			messageModel.setCode(0);
			messageModel.setMessage("Session expired, please log in.");
			BaseServlet.forwardToUrl(request, response, messageModel, "login.jsp");
		}else {
			User user = (User)session.getAttribute("user");
			if(user == null) {
				logger.error("Invalid session.");
				messageModel.setCode(0);
				messageModel.setMessage("Session expired, please log in.");
				BaseServlet.forwardToUrl(request, response, messageModel, "login.jsp");
			}else {
				Account account = new Account();
				account.setName(name);
				account.setUserId(user.getId());
				messageModel = accountService.addAccount(account);
				if(messageModel.getCode()==0) {
					BaseServlet.forwardToUrl(request, response, messageModel, "AddAccount.jsp");
				}else {
					BaseServlet.redirectToUrl(request, response, user, messageModel, "AddAccount.jsp");
				}
			}
		}
	}
}
