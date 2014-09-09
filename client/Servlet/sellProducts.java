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
 * Servlet implementation class sellProducts
 */
public class sellProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ServiceProxy proxy=new ServiceProxy();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sellProducts() {
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
		System.out.println("inside servlet sellProducts");
		HttpSession session=request.getSession();
		String EmailId=session.getAttribute("EmailId").toString();
		String productName=request.getParameter("ProductName");
		String Description=request.getParameter("Description");
		int price=Integer.parseInt(request.getParameter("Price").toString());
		int qty=Integer.parseInt(request.getParameter("Quantity").toString());
		String CategoryName=request.getParameter("categoryName");
		String sellerInfo=request.getParameter("info");
		proxy.sellProduct(EmailId, productName, Description, qty, price,CategoryName,sellerInfo);
		
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/View/SellProducts.jsp");
		rd.forward(request, response);
	}

}
