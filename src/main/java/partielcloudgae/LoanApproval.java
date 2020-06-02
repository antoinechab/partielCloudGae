package partielcloudgae;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import entity.Approval;


@WebServlet("/loanapproval")
public class LoanApproval extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoanApproval() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			int somme = Integer.parseInt(request.getParameter("somme"));
			
			String id = request.getParameter("id");
			
			URLConnection connection = new URL("https://partielcloud.herokuapp.com/AccManager/"+id).openConnection();
			InputStream result = connection.getInputStream();
			String compte = result.toString();
			
			//le compte doit retourner un json pour que je puisse le traiter
			
//			int risk = compte.risk;
			int risk = 1;
			
			AppManager am = new AppManager();
			
			String nomApproval = request.getParameter("nom");
    		int montantApproval = Integer.parseInt(request.getParameter("montant"));
    		boolean  riskApproval = Boolean.parseBoolean(request.getParameter("risk"));
    		boolean  decisionApproval = Boolean.parseBoolean(request.getParameter("decision"));
			
			if(somme>=1000 || risk == 1) {
				boolean decision = am.requeteApproval();
				if(decision == true) {
					Approval app = am.createApproval(nomApproval, montantApproval,riskApproval,decisionApproval);
					response.setStatus(200);
					response.getWriter().append("approved");
				
				}else {
					response.getWriter().append("disapproved");
				}
			}else {			
				if(risk == 1) {
					boolean decision = am.requeteApproval();
					
					if(decision == true) {
						Approval app = am.createApproval(nomApproval, montantApproval,riskApproval,decisionApproval);
						response.setStatus(200);
						response.getWriter().append("approved");
					
					}else {
						response.getWriter().append("disapproved");
					}
				}else {
					Approval app = am.createApproval(nomApproval, montantApproval,riskApproval,decisionApproval);
					
//					float montant = compte.montant;
					float montant = 666;
				
					response.setStatus(200);
					response.getWriter().append("approved et somme a: "+ montant);
				}
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
}
