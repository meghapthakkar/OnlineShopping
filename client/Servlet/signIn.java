package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import Connection.ServiceProxy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class signIn
 */
public class signIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ServiceProxy proxy=new ServiceProxy();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String qdone="";
		String endpoint="http://localhost:8080/Example/services/Service";
		proxy.setEndpoint(endpoint);
		try{
			if(request.getParameter("user")!="" || request.getParameter("pass")!=""){
			
			
			String user=request.getParameter("user");
			String pass=request.getParameter("pass");
			
			if(user.indexOf("@")==-1 || user.indexOf(".")==-1){
				request.setAttribute("msg", "Please enter a valid user id. It should be an email id");
				request.getRequestDispatcher("/View/signin.jsp").forward(request, response);
				
			}
			else
			{
			HttpSession session=request.getSession();
			qdone=proxy.signin(user, pass);
			if(qdone.substring(0,4).equals("true")){
				System.out.println(qdone);
				session.setAttribute("username",proxy.getUserName(user));
				session.setAttribute("EmailId", user);
				String categories[]=qdone.split("\\$");
				System.out.println(categories.length);
				request.setAttribute("categories",categories);
				String lastLogged=proxy.getLastLogged(user);
				request.setAttribute("lastLogged", lastLogged);
				System.out.println("ll"+lastLogged);
				proxy.setLastLoggedin(user);
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/View/displayCategories.jsp");
				rd.forward(request,response);
			}
			else{
				request.setAttribute("msg", "Invalid Login Credentials");
				request.getRequestDispatcher("/View/signin.jsp").forward(request, response);

			}
			}
			}
			else
			{
				request.setAttribute("msg", "Please fill all required fields");
				request.getRequestDispatcher("/View/signin.jsp").forward(request, response);

			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
			
	}

}
