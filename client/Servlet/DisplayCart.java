package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Connection.ServiceProxy;
/**
 * Servlet implementation class DisplayCart
 */
public class DisplayCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy=new ServiceProxy();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayCart() {
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
		ArrayList<String[]> details=new  ArrayList<String[]>();
		String cart=proxy.displayCart(emailId);
		if(cart!=null){
			String products[]=cart.split("!");
			for(int i=0;i<products.length;i++){
				String detail[]=products[i].split(";");
				details.add(detail);
				}
		}
		request.setAttribute("cart",details);
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/View/DisplayCart.jsp");
		rd.forward(request, response);
	}

}
