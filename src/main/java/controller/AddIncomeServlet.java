package controller;

import java.io.IOException;
import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.Income;
import entity.User;
import entity.vo.MessageModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.IncomeService;
import util.StringUtil;

@SuppressWarnings("serial")
@WebServlet("/addIncomeServlet")
public class AddIncomeServlet extends HttpServlet{
	
	private static final Logger logger = LogManager.getLogger(RegisterServlet.class);
	IncomeService incomeService = new IncomeService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		MessageModel messageModel = new MessageModel();
		User user;
		HttpSession session = request.getSession();
		String name = request.getParameter("income_name");
		String amount = request.getParameter("income_amount");
		String date = request.getParameter("income_date");
		String description = request.getParameter("income_description");
		if(StringUtil.isEmpty(name) ||StringUtil.isEmpty(amount)||StringUtil.isEmpty(date)) {
			messageModel.setCode(0);
			messageModel.setMessage("Name, amount, and date cannot be empey.");
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
				LocalDate ldate = LocalDate.parse(date);
				Income income = new Income(name,amountNum,ldate,description);
				income.setUserId(user.getId());
				messageModel = incomeService.addIncome(income);
				BaseServlet.redirectToUrl(request, response, user, messageModel, "index.jsp");
				
			}
		}		
	}

}
