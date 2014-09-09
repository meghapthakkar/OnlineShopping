package Connection;

import java.sql.*;
import java.util.ArrayList;

import com.google.gson.*;

public class DatabaseConnection {
	

		Connection con = null;
		static ResultSet rs;
	    Statement stmt = null;
	    
	    DatabaseConnection(){
	    	try{
	    		Class.forName("com.mysql.jdbc.Driver").newInstance();
	    		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amazon","root","jerrymouse");
	    		stmt = con.createStatement();
	    		if(!con.isClosed()){
	    			System.out.println("connection successful");
	    		}
	    	}catch(SQLException e){
	    		e.printStackTrace();
	    	}catch(InstantiationException e){
	    		e.printStackTrace();	    		
	    	}
	    	catch(IllegalAccessException e){
	    		e.printStackTrace();
	    	}catch(ClassNotFoundException e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    public String signup(String Fname,String Lname,String EmailId, String password){
			int count;
			String result = "";
			System.out.println("signup is called!!");
			try{
				String query = "INSERT INTO user_details (Fname ,Lname,EmailId, password) values ('"+ Fname+"','"+Lname+"','"+EmailId+"','"+password +"')";
				count = stmt.executeUpdate(query);
				if(count >=1){
					result = "true";
	    			System.out.println("insert successfull");
	    			}
	    		else{
	    			System.out.println("failed");
	    		}
			}catch(SQLException s){
				s.printStackTrace();
			}
			
			int uid=getUserId(EmailId);
			System.out.println(uid+"uid in signup");
			String insertCart="insert into cart (UID) value "+uid;
			try {
				stmt.executeUpdate(insertCart);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			/*String makeHistoryEntry="insert into user_history (UID,productBought) values ("+getUserId(EmailId)+",null)";
			try {
				stmt.executeUpdate(makeHistoryEntry);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	    	return result;
	    	
	    }
	    
	    public String SignIn(String name,String password){
	    	int count;
	    	String result="";
	    	DatabaseConnection db=new DatabaseConnection();
	    	System.out.println("Signin is called");
	    	try{
	    		String query="select * from user_details where EmailId='"+name+"' and password='"+password+"'";
	    		ResultSet rs = stmt.executeQuery(query);
	    		if(rs.next()){
	    			//System.out.println(rs.next());
	    			result="true"+categories();
	    			System.out.println("Logging in the user"+result);
	    	    //	String logHistory="update user_history set LastLoginDate=now() where UID="+rs.getInt("Uid");
	    	    	//Statement stmt2=con.createStatement();
	    	    //	stmt2.executeUpdate(logHistory);
	    		}
	    		else{
	    			result="false";
	    			System.out.println("couldnt log in");
	    		}
	    	}catch(SQLException e){
	    		e.printStackTrace();
	    	}
	    	String lastLogged=getLastLogged(name);
	    	
	   	

	    	return result;
	    }
	    
	    public String categories() throws SQLException{
	    	String getcategories="select CategoryName from catalog ";
	    	ResultSet rs=stmt.executeQuery(getcategories);
	    	String categories="";
	    	System.out.println("inside categories");
	    	while(rs.next()){
	    		categories+="$"+rs.getString("CategoryName");
	    		System.out.println("catag: "+categories);
	    				}
	    //	b.setCategoryName("");
			return categories;
	    }
	    
	    public String products(String categoryName, String username){
	    	int id=getUserId(username);
	    	String getProducts="select ProductName,Description,Price,Quantity,Fname,info from catalog,product,pro_cat,user_details where catalog.CategoryName='"+categoryName+"' AND catalog.idCatalog=pro_cat.catId AND product.idProduct=pro_cat.prodId AND user_details.Uid=product.SellerId and SellerId not in ("+id+")";
	    	String productList="";
			
	    	try {
	    		ResultSet rs=stmt.executeQuery(getProducts);
				while(rs.next()){
					productList+="!"+rs.getString("ProductName")+";"+rs.getString("Description")+";"+rs.getInt("Price")+";"+rs.getString(5)+";"+rs.getString("info")+";"+rs.getInt(4);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	return productList;	    				    	
	    }

	    public String getUserName(String EmailId){
	    	String getUname="select Fname from user_details where EmailId='"+EmailId+"'";
	    	String uname="";
	    	try{
	    		ResultSet rs=stmt.executeQuery(getUname);
	    		if(rs.next()){
	    			uname=rs.getString("Fname");
	    		}
	    		}
	    		catch(Exception e){
	    			e.printStackTrace();
	    		}
	    		return uname;
	    	}
	    
	    public int getUserId(String EmailId){
	    	String getUname="select * from user_details where EmailId='"+EmailId+"'";
	    	int uid=0;
	    	try{
	    		ResultSet rs=stmt.executeQuery(getUname);
	    		if(rs.next()){
	    			uid=rs.getInt("Uid");
	    		}
	    		}
	    		catch(Exception e){
	    			e.printStackTrace();
	    		}
	    		return uid;
	    	
	    	
	    }
	    
	    public int getCartId(String EmailId){
	    	String getUname="select CartID from cart where UID=(select Uid from user_details where EmailId='"+EmailId+"')";
	    	int cartid=0;
	    	try{
	    		ResultSet rs=stmt.executeQuery(getUname);
	    		if(rs.next()){
	    			cartid=rs.getInt("CartID");
	    		}
	    		}
	    		catch(Exception e){
	    			e.printStackTrace();
	    		}
	    		return cartid;	    	
	    }
	    
	    public int getProductId(String ProductName){
	    	String getProductid="select idProduct from product where ProductName='"+ProductName+"'";
	    	int id=0;
	    	try{
	    		ResultSet rs=stmt.executeQuery(getProductid);
	    		if(rs.next()){
	    			id=rs.getInt("idProduct");
	    			
	    		}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	return id;
	    }
	    
	    public int getCost(int ProductId){
	    	String query="select Price from product where idProduct="+ProductId;
	    	int cost=0;
	    	try{
	    		ResultSet rs=stmt.executeQuery(query);
	    		if(rs.next()){
	    			cost=rs.getInt("Price");
	    		}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	return cost;
	    }
	    
	    public int getQuantity(String productName){
	    	String query="select Quantity from product where ProductName='"+productName+"'";
	    	int quantity=0;
	    	try{
	    		ResultSet rs=stmt.executeQuery(query);
	    		if(rs.next()){
	    			quantity=rs.getInt("Quantity");
	    		}
	    	}catch(SQLException e){
	    		e.printStackTrace();
	    	}
	    	return quantity;
	    }
	    
	    public void addToCart(String productName,String emailId,int Qty){
	    	int userId=getUserId(emailId);
	    	int cartId=getCartId(emailId);
	    	int proId=getProductId(productName);
	    	int cost=getCost(proId);
	    	int amount=Qty*cost;
	    	
	    	String addToCart="insert into cart_product (cartID,ProductId,QuantityBought,amount) values ("+cartId+","+proId+","+Qty+","+Qty*cost+")";
	    	String updateTotalAmount="update cart set TotalAmount=TotalAmount+"+amount+" where UID="+userId; 
	    	try{
	    		con.setAutoCommit(false);
	    		stmt.addBatch(addToCart);
	    		stmt.addBatch(updateTotalAmount);
	    		int[] a=stmt.executeBatch();
	    		System.out.println("printing batch results in add to cart"+a);
	    		con.commit();
	    	}catch(SQLException e){
	    		e.printStackTrace();
	    	}
	    	System.out.println("buy product within server"+productName+" "+ emailId);
	    }
	    
	    public String displayCart(String emailId){
	    	String cart="";
	    	String getCart="select ProductName,QuantityBought from cart_product,product,cart where cart.UID=(select UID from user_details where EmailId='"+emailId+"') and cart_product.cartID=cart.CartID and cart_product.productID=product.idProduct";
	    	try{
	    		ResultSet rs=stmt.executeQuery(getCart);
	    		while(rs.next()){
	    			cart+="!"+rs.getString("ProductName")+";"+rs.getInt("QuantityBought");
	    		}
	    	}catch(SQLException e){
	    		e.printStackTrace();
	    	}
	    	return cart;
	    }
	    
	    public boolean checkout(String EmailId,String creditDetails) throws SQLException{
	    	boolean success=false;
	    	if(creditDetails.length()==16){
	    		success=true;
	    		System.out.println("inside server checkout");
	    		String productIDs="select ProductId,ProductName,QuantityBought,product.SellerId from cart_product,product where cartID=(select cartID from cart where UID=(select Uid from user_details where EmailID='"+EmailId+"')) and cart_product.ProductId=product.idProduct";
	    		try{
	    			int prodid[]=new int[10];
	    			int qty[]=new int[10];
	    			int sellerId[]=new int[10];
	    			String ProductName[]=new String[10];
	    			ResultSet rs=stmt.executeQuery(productIDs);
	    			int i=0;
	    			while(rs.next()){
	    				System.out.println("inside rsnext ");
	    				prodid[i]=rs.getInt("ProductID");
	    				ProductName[i]=rs.getString("ProductName");
	    				qty[i]=rs.getInt("QuantityBought");
	    				sellerId[i]=rs.getInt("product.SellerId");
	    				i++;
		    			}
	    			int userId=getUserId(EmailId);
	    			for(int j=0;j<prodid.length;j++){
	    				String updateQty="update product set Quantity=Quantity-"+qty[j]+" where idProduct="+prodid[j]+"";
	    	    		Statement stmt1 = con.createStatement();
	    	    		stmt1.executeUpdate(updateQty);
	    	    		String purchaseHistory="insert into user_history (UID,product) values ("+userId+",'"+ProductName[j]+"')";
	    	    		String sellerHistory="insert into user_history (UID,product,boughtsold) values ("+sellerId[j]+",'"+ProductName[j]+"',2)";
	    	    		Statement stmt3=con.createStatement();
	    	    		stmt3.executeUpdate(purchaseHistory);
	    	    		Statement stmt4=con.createStatement();

	    	    		stmt4.executeUpdate(sellerHistory);

	    			}
	    		}catch(SQLException e){
	    			e.printStackTrace();
	    		}
	    		int cartId=getCartId(EmailId);
	    		String updateCartAmount="update cart set TotalAmount=0 where CartID="+cartId;
	    		String delcartentries="delete from cart_product where CartID="+cartId;
	    		Statement stmt2 = con.createStatement();

	    		try{
	    			con.setAutoCommit(false);
	    			stmt2.addBatch(updateCartAmount);
	    			stmt2.addBatch(delcartentries);
	    			stmt2.executeBatch();
	    			con.commit();
	    		}
	    		catch(SQLException e){
	    			e.printStackTrace();
	    		}
	    	}
	    	
	    	
	    	return success;
	    	
	    }
	    
	    public String getLastLogged(String EmailId){
	    	String query="select LastLoginDate from user_history where UID="+getUserId(EmailId);
	    	String lastLogged="";
	    	try {
				ResultSet rs=stmt.executeQuery(query);
				if(rs.next()){
				lastLogged=rs.getTime("LastLoginDate").toString()+" "+rs.getDate("LastLoginDate");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return lastLogged;
	    }
	    
	    public void setLastLoggedin(String EmailId){
	     	String enterlstLogged="update user_history set LastLoginDate=now() where UID="+getUserId(EmailId);
	    	try {
				stmt.executeUpdate(enterlstLogged);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	    }
	    
	    public void sellProduct(String EmailId,String productName,String description,int qty,int price,String categoryName,String info){
	    	String sell="insert into product (ProductName,Description,Price,Quantity,sellerId,info) values ('"+productName+"','"+description+"',"+price+","+qty+",(select Uid from user_details where EmailId='"+EmailId+"'),'"+info+"')";
	    	String setCategory="insert into pro_cat (prodId,catId) values ((select idProduct from product where ProductName='"+productName+"'),(select idCatalog from catalog where CategoryName='"+categoryName+"'))";
	    	try {
				stmt.executeUpdate(sell);
				Statement stmt2=con.createStatement();
				stmt2.executeUpdate(setCategory);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    public String history(String EmailId){
	    	int uid=getUserId(EmailId);
	    	String history="";
	    	System.out.println("UserId withing history"+uid);
	    	String query="select distinct product from user_history where UID="+uid+" and boughtsold=1";
	    	String query2="select distinct product from user_history where UID="+uid+" and boughtsold=2";
	    	try {
				ResultSet r=stmt.executeQuery(query);
				while(r.next()){
					String productName=r.getString("product");
					
					history+="!"+productName;
					System.out.println(history+"history");
				}
				history+="%";
				ResultSet rs=stmt.executeQuery(query2);
				while(rs.next()){
					String productName=rs.getString("product");
					history+="!"+productName;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return history;
	    }
	    
}

