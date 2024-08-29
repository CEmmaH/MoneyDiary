package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
@WebServlet("/expenseReportServlet")
public class ExpenseReportServlet extends HttpServlet{
	private static final Logger logger = LogManager.getLogger(RegisterServlet.class);
	ExpenseService expenseService = new ExpenseService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String orderby = request.getParameter("orderby");
		if(StringUtil.isEmpty(orderby)) {
			orderby="date";
		}
		
		MessageModel messageModel = new MessageModel();
		HttpSession session = request.getSession();
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
				List<Expense> expenseList = new ArrayList<>();
				expenseList = expenseService.getExpenseTrans(user.getId(),orderby);
				messageModel.setCode(1);
				messageModel.setMessage("");
				messageModel.setObject(expenseList);
		    	request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("messageModel", messageModel);
				request.getSession().setAttribute("expenseList", expenseList);
				request.getSession().setAttribute("activeTab", "tab_Expense_Report");
				String redirectUrl = "ExpenseReport.jsp"; 
				response.sendRedirect(redirectUrl);
//				BaseServlet.redirectToUrl(request, response, user, messageModel,"index.jsp","tab_Expense_Report");
			}
		}
		
	}
}
