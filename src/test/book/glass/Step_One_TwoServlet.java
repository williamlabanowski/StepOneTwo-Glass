package test.book.glass;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





@SuppressWarnings("serial")
public class Step_One_TwoServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		StepOneTwo.insertSimpleCard(req);
		resp.setContentType("text/plain");
		resp.getWriter().append("A timeline card has just been inserted!");
		
		/*resp.setContentType("text/html; charset=utf-8");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("video", StepOneTwo.getRandomVideoName());
	    String html = StepOneTwo.render(getServletContext(), "web/video.ftl", data);
	    resp.getWriter().append(html);*/
	}
	
	
	
}
