package controller;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CategoryService;

@SuppressWarnings("serial")
@WebServlet("/deleteECategory")
public class DeleteECategoryServlet extends HttpServlet{
	
	private static final Logger logger = LogManager.getLogger(RegisterServlet.class);
	CategoryService categoryService = new CategoryService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = request.getReader()){
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
        }
        try {
	        JSONObject json = new JSONObject(sb.toString());
	        String ids = json.getString("id"); // 获取 ID
	        int id;
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
	        if(ids != null && ids != "") {
	        	id = Integer.parseInt(ids);
	        	categoryService.deleteECategoryById(id);
	        }else {
	        	logger.error("ID is null or empty.");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"message\": \"Invalid ID.\"}");
	        }
	        response.setContentType("application/json");
	        response.setStatus(HttpServletResponse.SC_OK);
	        response.getWriter().write("{\"message\": \"Category deleted successfully.\"}");
        }catch (NumberFormatException e) {
            logger.error("Invalid ID format: " + sb.toString(), e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\": \"Invalid ID format.\"}");
        } catch (Exception e) {
            logger.error("Error processing request.", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"message\": \"Internal server error.\"}");
        }

    }
}
