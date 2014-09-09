/**
 * Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package Connection;

public interface Service extends java.rmi.Remote {
    public java.lang.String getCategories() throws java.rmi.RemoteException;
    public java.lang.String history(java.lang.String emailId) throws java.rmi.RemoteException;
    public int getProductId(java.lang.String productName) throws java.rmi.RemoteException;
    public java.lang.String getUserName(java.lang.String emailId) throws java.rmi.RemoteException;
    public boolean checkout(java.lang.String emailId, java.lang.String creditcard) throws java.rmi.RemoteException;
    public int getCartId(java.lang.String emailId) throws java.rmi.RemoteException;
    public int getUserid(java.lang.String emailId) throws java.rmi.RemoteException;
    public java.lang.String signup(java.lang.String fname, java.lang.String lname, java.lang.String emailId, java.lang.String pass) throws java.rmi.RemoteException;
    public java.lang.String signin(java.lang.String name, java.lang.String pass) throws java.rmi.RemoteException;
    public int getQuantity(java.lang.String productName) throws java.rmi.RemoteException;
    public java.lang.String getLastLogged(java.lang.String emailId) throws java.rmi.RemoteException;
    public void addToCart(java.lang.String productName, java.lang.String emailId, int qty) throws java.rmi.RemoteException;
    public void sellProduct(java.lang.String emailId, java.lang.String productName, java.lang.String description, int qty, int price, java.lang.String categoryName, java.lang.String info) throws java.rmi.RemoteException;
    public java.lang.String displayCart(java.lang.String emailId) throws java.rmi.RemoteException;
    public void setLastLoggedin(java.lang.String emailId) throws java.rmi.RemoteException;
    public java.lang.String getProductList(java.lang.String categoryName, java.lang.String userName) throws java.rmi.RemoteException;
}
