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
 * Servlet implementation class ViewMyHistory
 */
public class ViewMyHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ServiceProxy proxy=new ServiceProxy();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMyHistory() {
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
		// TODO Auto-genera
		HttpSession session=request.getSession();
		String emailId=session.getAttribute("EmailId").toString();
		String result=proxy.history(emailId);
		
		String products[]=result.split("%");
		String soldItems[]=products[1].split("!");
		String boughtProducts[]=products[0].split("!");
		System.out.println("within view post");
		request.setAttribute("sold",soldItems );
		request.setAttribute("bought", boughtProducts);
		
		request.getRequestDispatcher("/View/history.jsp").forward(request, response);
		
	}

}
