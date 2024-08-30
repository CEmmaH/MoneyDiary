package controller;

import java.io.IOException;
import java.util.List;

import entity.Account;
import entity.Category;
import entity.User;
import entity.vo.MessageModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AccountService;
import service.CategoryService;
import service.UserService;

@SuppressWarnings("serial")
@WebServlet("/login")
public class UserServlet extends HttpServlet{
	private UserService userService = new UserService();
	CategoryService categoryService = new CategoryService();
	AccountService accountService = new AccountService();
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
			User user = (User)messageModel.getObject();
			List<Category> categories = categoryService.getECategoriesByUserId(user.getId());
			List<Account> accountList = accountService.getAccountsByUserId(user.getId());

			req.getSession().setAttribute("user", user);
			req.getSession().setAttribute("eCategory", categories);
			req.getSession().setAttribute("accountList", accountList);
			req.getSession().setAttribute("activeTab", "tab_Expense_Trans");
			resp.sendRedirect("index.jsp");
		}else { //failed
			BaseServlet.forwardToUrl(req, resp, messageModel, "login.jsp");
		}
	}

}
