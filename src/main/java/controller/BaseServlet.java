package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import entity.User;
import entity.vo.MessageModel;

@SuppressWarnings("serial")
public abstract class BaseServlet extends HttpServlet {
    protected static void forwardToRegister(HttpServletRequest request, HttpServletResponse response, MessageModel messageModel) throws ServletException, IOException {
        request.setAttribute("messageModel", messageModel);
        request.getRequestDispatcher("Register.jsp").forward(request, response);
    }
    protected static void forwardToUrl(HttpServletRequest request, HttpServletResponse response, MessageModel messageModel,String url) throws ServletException, IOException {
        request.setAttribute("messageModel", messageModel);
        request.getRequestDispatcher(url).forward(request, response);
    }
    protected static void redirectToUrl(HttpServletRequest request, HttpServletResponse response,User user, MessageModel messageModel,String url) throws ServletException, IOException {
    	request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("messageModel", messageModel);
		response.sendRedirect(url);
    }
}
