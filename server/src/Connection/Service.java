package Connection;

import java.sql.SQLException;

import javax.jws.WebService;

public class Service {
	
	 DatabaseConnection db = new DatabaseConnection();
	    
	    public String signup(String Fname,String Lname, String EmailId,String pass) throws SQLException{
	    	System.out.println("inside signup");
	    	String result = "";
	    	
	    	result = db.signup(Fname,Lname,EmailId, pass);
	    	
	    	return result;
	    }

	    public String signin(String name,String pass){
	    	System.out.println("Inside signin");
	    	String result="";  	
	    	result=db.SignIn(name, pass);
	    	return result;
	    }
	    
	    public String getProductList(String categoryName,String userName){
	    	System.out.println("inside getProducts");
	    	String result = "";
	    	
	    	result = db.products(categoryName,userName);
	    	
	    	return result;
	    }
	    
	    public String getCategories() {
	    	String str="";
	    	try {
	    		
				str= db.categories();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return str;
	    	
	    }
	    
	    public String getUserName(String EmailId){
	    	return db.getUserName(EmailId);
	    }
	    
	    public int getUserid(String EmailId){
	    	return db.getUserId(EmailId);
	    }
	    
	    public int getCartId(String EmailId){
	    	return db.getCartId(EmailId);
	    }
	    public int getProductId(String ProductName){
	    	return db.getProductId(ProductName);
	    }
	    
	    public void addToCart(String productName,String emailId,int Qty){
	    	db.addToCart(productName,emailId,Qty);
	    }
	    public int getQuantity(String productName){
	    	return db.getQuantity(productName);
	    }
	    
	    public String displayCart(String emailId){
	    	return db.displayCart(emailId);
	    }
	    
	    public boolean checkout(String emailId,String creditcard) throws SQLException{
	    	return db.checkout(emailId, creditcard);
	    }
	    
	    public String getLastLogged(String EmailId){
	    	return db.getLastLogged(EmailId);
	    }

	    public void setLastLoggedin(String EmailId){
	    	db.setLastLoggedin(EmailId);
	    }
	    
	    public void sellProduct(String EmailId,String productName,String description,int qty,int price,String categoryName,String info){
	    	db.sellProduct(EmailId, productName, description, qty, price,categoryName,info);
	    }
	    
	    public String history(String EmailId){
	    	return db.history(EmailId);
	    }
	    
}