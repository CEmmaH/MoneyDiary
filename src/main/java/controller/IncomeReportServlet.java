package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@SuppressWarnings("serial")
@WebServlet("/incomeReportServlet")
public class IncomeReportServlet extends HttpServlet{
	private static final Logger logger = LogManager.getLogger(RegisterServlet.class);
	IncomeService incomeService = new IncomeService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String orderby = request.getParameter("orderby_income");
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
				List<Income> incomeList = new ArrayList<>();
				incomeList = incomeService.getIncometrans(user.getId(), orderby);
				messageModel.setCode(1);
				messageModel.setMessage("");
				messageModel.setObject(incomeList);
				request.getSession().setAttribute("incomeList", incomeList);
				BaseServlet.redirectToUrl(request, response, user, messageModel,"index.jsp","tab_Income_Report");
			}
		}
	}
}
