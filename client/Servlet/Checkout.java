package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Connection.ServiceProxy;
/**
 * Servlet implementation class Checkout
 */
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ServiceProxy proxy=new ServiceProxy();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
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
		HttpSession session=request.getSession();
		String emailId=(String) session.getAttribute("EmailId");
		String credit=(String) request.getParameter("creditCard");
		System.out.println("inside checkout servlet"+emailId+credit);
			if(credit.length()==0){
				request.setAttribute("msg","please enter a value");
				request.getRequestDispatcher("/View/checkout.jsp").forward(request, response);
			}
			else if(credit.length()==16){
			proxy.checkout(emailId, credit);
			
			String qdone=proxy.getCategories();
			String categories[]=qdone.split("\\$");
			System.out.println(categories.length);
			request.setAttribute("categories",categories);
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/View/displayCategories.jsp");
			rd.forward(request,response);		
			}
			else {
				request.setAttribute("msg","invalid card info");
				request.getRequestDispatcher("/View/checkout.jsp").forward(request, response);
			}
		
	}

}
