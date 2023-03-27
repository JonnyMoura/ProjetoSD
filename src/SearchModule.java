
import java.rmi.*;

public interface SearchModule extends Remote{
	public void print_on_client(String s) throws java.rmi.RemoteException;
}
