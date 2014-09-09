package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis.session.Session;

import Connection.ServiceProxy;
/**
 * Servlet implementation class buyProduct
 */
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ServiceProxy proxy=new ServiceProxy();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
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
		System.out.println("inside doPost of addtocart");
		HttpSession session=request.getSession();
		
		String EmailId=(String)session.getAttribute("EmailId");
				
		String endpoint="http://localhost:8080/Example/services/Service";
		String productName=request.getParameter("button");
		System.out.println(productName);
		int quantity=Integer.parseInt(request.getParameter(productName));
		System.out.println("Quantity is "+quantity);
		
		proxy.addToCart(productName,EmailId,quantity);
		qdone=proxy.getCategories();
		String categories[]=qdone.split("\\$");
		System.out.println(categories.length);
		request.setAttribute("categories",categories);
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/View/displayCategories.jsp");
		rd.forward(request,response);
	//response.sendRedirect("/View/displayCategories.jsp");
		


	}

}
