package partielcloudgae;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;

import entity.Approval;


@WebServlet("/appmanager")
public class AppManager extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppManager() {}
    
    public boolean requeteApproval() {
    	//TODO demander au banquier si il accepte ou non le crédit
    	return true;
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	try {
    		if(request.getParameterMap() == null || request.getParameterMap().isEmpty()) {
    			String s = getAll();
    			response.setStatus(200);
    			response.getWriter().append(s);
    		}else {
    			String id = request.getParameter("id");
    			Long num = Long.parseLong(id);
    			Approval c = ofy().load().type(Approval.class).id(num).now();
    			String x = c.toString();
    			response.setStatus(200);
    			response.getWriter().append(x);
    		}
		}catch(NumberFormatException e ){
			response.setStatus(415);
			response.getWriter().append("mauvais format de données ");
		}
    	catch(Exception e) {
			response.setStatus(500);
			response.getWriter().append("Erreur: " + e);
		}
	}
    
    protected String getAll() {
    	List<Approval> c = ofy().load().type(Approval.class).list();
		return c.toString();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		String nom = request.getParameter("nom");
    		int montant = Integer.parseInt(request.getParameter("montant"));
    		boolean  risk = Boolean.parseBoolean(request.getParameter("risk"));
    		boolean  decision = Boolean.parseBoolean(request.getParameter("decision"));
    		Approval c = createApproval(nom, montant,risk,decision);
			ofy().save().entity(c).now();
			response.setStatus(200);
    		}catch(NumberFormatException e ){
    			response.setStatus(415);
    			response.getWriter().append("mauvais format de données");
    		}
	    	catch(Exception e) {
	    			response.setStatus(500);
	    			response.getWriter().append("Erreur: " + e);
    		}
	}
    
    protected Approval createApproval(String nom, int montant, boolean risk, boolean decision) throws Exception{
		Approval c = new Approval(nom, montant,risk,decision);
		ofy().save().entity(c).now();
    	return c;	
    }
    
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  	
	  	try {
	  		String id = request.getParameter("id");
			Long num = Long.parseLong(id);
	  	
    		String nom = request.getParameter("nom");
    		int montant = Integer.parseInt(request.getParameter("montant"));
    		boolean  risk = Boolean.parseBoolean(request.getParameter("risk"));
    		boolean  decision = Boolean.parseBoolean(request.getParameter("decision"));
    		
    		Approval c = ofy().load().type(Approval.class).id(num).now();
    	  	c.setNom(nom);
    	  	c.setMontant(montant);
    	  	c.setRisk(risk);
    	  	c.setDecision(decision);
    	  	
    	  	ofy().save().entity(c).now();
			response.setStatus(200);
			
    		}catch(NumberFormatException e ){
    			response.setStatus(415);
    			response.getWriter().append("mauvais format de données");
    		}
	    	catch(Exception e) {
	    			response.setStatus(500);
	    			response.getWriter().append("Erreur: " + e);
    		}
    }
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		String id = request.getParameter("id");
			Long num = Long.parseLong(id);
			
    		ofy().delete().type(Approval.class).id(num).now();
			response.setStatus(200);    		
		}catch(NumberFormatException e ){
			response.setStatus(415);
			response.getWriter().append("mauvais format de données " + request.getParameter("id"));
		}
    	catch(Exception e) {
			response.setStatus(500);
			response.getWriter().append("Erreur: " + e);
		}
    }
    
    
      
}
