package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Connection.ServiceProxy;

/**
 * Servlet implementation class Products
 */
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ServiceProxy proxy=new ServiceProxy();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Products() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String qdone="";
		System.out.println("inside doPost");
		HttpSession session=request.getSession();
		String emailId=session.getAttribute("EmailId").toString();
		String endpoint="http://localhost:8080/Example/services/Service";
		
		String categoryName=request.getQueryString();
		qdone=proxy.getProductList(categoryName,emailId);
		
		proxy.setEndpoint(endpoint);
		try{
		//	HttpSession session=request.getSession();
			if(qdone!=""){
				System.out.println("qdone "+qdone);
			//	ArrayList<String> productDetails=new ArrayList<String>();
				ArrayList<String[]> productList=new ArrayList<String[]>();
				String[] product=qdone.split("!");
				for(int i=0;i<product.length;i++){
					System.out.println(product[i]);
				}
				
				
				for(int i=1;i<product.length;i++){
				
					String productDetails[]=product[i].split(";");
					for(int j=0;j<productDetails.length;j++){
					System.out.println(productDetails[j]);
					}
					productList.add(productDetails);
				}
				
				request.setAttribute("productList",productList);
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/View/displayProducts.jsp");
				rd.forward(request,response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
	}

}
