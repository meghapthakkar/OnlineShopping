package Connection;

public class ServiceProxy implements Connection.Service {
  private String _endpoint = null;
  private Connection.Service service = null;
  
  public ServiceProxy() {
    _initServiceProxy();
  }
  
  public ServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceProxy();
  }
  
  private void _initServiceProxy() {
    try {
      service = (new Connection.ServiceServiceLocator()).getService();
      if (service != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)service)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)service)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (service != null)
      ((javax.xml.rpc.Stub)service)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public Connection.Service getService() {
    if (service == null)
      _initServiceProxy();
    return service;
  }
  
  public java.lang.String getCategories() throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.getCategories();
  }
  
  public java.lang.String history(java.lang.String emailId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.history(emailId);
  }
  
  public int getProductId(java.lang.String productName) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.getProductId(productName);
  }
  
  public java.lang.String getUserName(java.lang.String emailId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.getUserName(emailId);
  }
  
  public boolean checkout(java.lang.String emailId, java.lang.String creditcard) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.checkout(emailId, creditcard);
  }
  
  public int getCartId(java.lang.String emailId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.getCartId(emailId);
  }
  
  public int getUserid(java.lang.String emailId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.getUserid(emailId);
  }
  
  public java.lang.String signup(java.lang.String fname, java.lang.String lname, java.lang.String emailId, java.lang.String pass) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.signup(fname, lname, emailId, pass);
  }
  
  public java.lang.String signin(java.lang.String name, java.lang.String pass) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.signin(name, pass);
  }
  
  public int getQuantity(java.lang.String productName) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.getQuantity(productName);
  }
  
  public java.lang.String getLastLogged(java.lang.String emailId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.getLastLogged(emailId);
  }
  
  public void addToCart(java.lang.String productName, java.lang.String emailId, int qty) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    service.addToCart(productName, emailId, qty);
  }
  
  public void sellProduct(java.lang.String emailId, java.lang.String productName, java.lang.String description, int qty, int price, java.lang.String categoryName, java.lang.String info) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    service.sellProduct(emailId, productName, description, qty, price, categoryName, info);
  }
  
  public java.lang.String displayCart(java.lang.String emailId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.displayCart(emailId);
  }
  
  public void setLastLoggedin(java.lang.String emailId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    service.setLastLoggedin(emailId);
  }
  
  public java.lang.String getProductList(java.lang.String categoryName, java.lang.String userName) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.getProductList(categoryName, userName);
  }
  
  
}