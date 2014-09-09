package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Connection.ServiceProxy;

/**
 * Servlet implementation class SignUp
 */
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy=new ServiceProxy();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
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
		System.out.println("in the post method of sign up");
		String Fname=request.getParameter("Fname");
		String Lname=request.getParameter("Lname");
		String EmailId=request.getParameter("EmailId");
		String Password=request.getParameter("Password");
		String endpoint="http://localhost:8080/Example/services/Service";
		
		if(request.getParameter("Fname")=="" || request.getParameter("Lname")=="" || request.getParameter("EmailId")=="" || request.getParameter("Password")=="" || request.getParameter("Password1")==""){
			request.setAttribute("msg", "Please enter all required fields");
			request.getRequestDispatcher("/View/signUp.jsp").forward(request, response);
		}
		else if(EmailId.indexOf("@")==-1 || EmailId.indexOf(".")==-1){
			request.setAttribute("msg", "Please enter a valid email id");
			request.getRequestDispatcher("/View/signUp.jsp").forward(request, response);
		
		}
		else
		{
			
			
		qdone=proxy.signup(Fname, Lname, EmailId, Password);
		
		proxy.setEndpoint(endpoint);
		try{
			if(qdone.substring(0, 4).equalsIgnoreCase("true")){
				request.getRequestDispatcher("/View/signin.jsp").forward(request, response);
			}else{
				System.out.println("query unsuccessfull");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		
	}

}
