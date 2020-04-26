package partielcloudgae;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.*;

@WebServlet(
	    name = "AppManager",
	    urlPatterns = {"/loanList"}
	)

public class AppManager extends HttpServlet {

	@Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

//	    response.setContentType("text/plain");
//	    response.setCharacterEncoding("UTF-8");
//
//	    response.getWriter().print("la liste!\r\n");
		
		//TODO faire le get		
		
	  }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
	
}
