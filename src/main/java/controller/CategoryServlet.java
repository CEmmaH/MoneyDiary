package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.Category;
import entity.User;
import entity.vo.MessageModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.CategoryService;
import util.StringUtil;

@SuppressWarnings("serial")
@WebServlet("/addcategory")
public class CategoryServlet extends HttpServlet {
	
	private static final Logger logger = LogManager.getLogger(RegisterServlet.class);
	CategoryService categoryService = new CategoryService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		String name = request.getParameter("name");
		HttpSession session = request.getSession(false);
		MessageModel messageModel = new MessageModel();
		if(session == null) {
			logger.error("Invalid Session.");
			messageModel.setMessage("Session expired, please log in.");
			messageModel.setCode(0);
			BaseServlet.forwardToUrl(request, response, messageModel, "AddCategory.jsp");
		}else {
			User user = (User)session.getAttribute("user");
			if(user==null) {
				logger.error("Invalid User");
				messageModel.setMessage("Session expired, please log in.");
				messageModel.setCode(0);
				BaseServlet.forwardToUrl(request, response, messageModel, "AddCategory.jsp");
			}else {
				Category category = new Category(name);				
				category.setUserId(user.getId());				
				messageModel.setObject(category);
				if(StringUtil.isEmpty(name)) {
					messageModel.setMessage("Category Name can't be empty, please enter category name.");
					messageModel.setCode(0);
					BaseServlet.forwardToUrl(request, response, messageModel, "AddCategory.jsp");
				}else {
					messageModel = categoryService.addCategory(category);
					if(messageModel.getCode()==0) {
						BaseServlet.forwardToUrl(request, response, messageModel, "AddCategory.jsp");
					}else {
						request.getSession().setAttribute("user", user);
						request.getSession().setAttribute("messageModel", messageModel);
						response.sendRedirect("AddCategory.jsp");
					}
				}
			}

		}
	}
	/**
	 * To call the service layer method and obtain expense category list.
	 * @param userid
	 * @return
	 */
	public List<Category> getCategoryList(int userid) {
		List<Category> categories = new ArrayList<>();
		categories = categoryService.getECategoriesByUserId(userid);
		return categories;
	}
}
