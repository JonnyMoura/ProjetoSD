import java.rmi.*;


public interface StorageBarrels_I extends Remote {
	public void print_on_server(String s) throws java.rmi.RemoteException;
  public void subscribe(String name, SearchModule c) throws RemoteException;
  public void search(String search) throws RemoteException;
}