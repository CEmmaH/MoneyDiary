package controller;

import java.io.IOException;
import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.Expense;
import entity.User;
import entity.vo.MessageModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ExpenseService;
import util.StringUtil;

@SuppressWarnings("serial")
@WebServlet("/addExpenseServlet")
public class AddExpenseServlet extends HttpServlet{
	
	private static final Logger logger = LogManager.getLogger(RegisterServlet.class);
	ExpenseService expenseService = new ExpenseService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		MessageModel messageModel = new MessageModel();
		User user;
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String amount = request.getParameter("amount");
		String category = request.getParameter("category_select");
		String account = request.getParameter("account_select");
		String date = request.getParameter("date");
		String description = request.getParameter("description");
		if(StringUtil.isEmpty(name) ||StringUtil.isEmpty(amount)||StringUtil.isEmpty(category)|| StringUtil.isEmpty(account)) {
			messageModel.setCode(0);
			messageModel.setMessage("Name, amount, category and account type cannot be empey.");
			BaseServlet.forwardToUrl(request, response, messageModel,"index.jsp");
		}
		if(session == null) {
			logger.error("Invalid Session.");
			messageModel.setMessage("Session expired, please log in.");
			messageModel.setCode(0);
			BaseServlet.forwardToUrl(request, response, messageModel, "login.jsp");
		}else {
			user = (User)session.getAttribute("user");
			if(user==null) {
				logger.error("Invalid User");
				messageModel.setMessage("Session expired, please log in.");
				messageModel.setCode(0);
				BaseServlet.forwardToUrl(request, response, messageModel, "login.jsp");
			}else {			
				double amountNum = Double.parseDouble(amount);
				int categoryId = Integer.parseInt(category);
				int accountId = Integer.parseInt(account);
				LocalDate ldate = LocalDate.parse(date);
				Expense expense = new Expense(name,amountNum,ldate,categoryId,accountId,user.getId(),description);
				messageModel = expenseService.addExpenseTransaction(expense);
				BaseServlet.redirectToUrl(request, response, user, messageModel, "index.jsp");				
			}
		}		
	}
}
