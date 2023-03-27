import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.Scanner;
import java.io.Serializable;


public class StorageBarrels extends UnicastRemoteObject implements Runnable, StorageBarrels_I, Serializable {
    static SearchModule client;

    public void run() {
        System.out.println("Hello Server ready.");
    }

	public StorageBarrels() throws RemoteException {
		super();
	}

	public void print_on_server(String s) throws RemoteException {
		System.out.println("> " + s);
	}

	public void subscribe(String name, SearchModule c) throws RemoteException {
		System.out.println("Subscribing " + name);
		System.out.print("> ");
		client = c;
	}

    public void search(String search) throws RemoteException {
        System.out.println("Searching for " + search);
    }

    
    public static void main(String args[]) {
        String a;

		
	
		

		try (Scanner sc = new Scanner(System.in)) {
			StorageBarrels barrel = new StorageBarrels();
			LocateRegistry.createRegistry(1099).rebind("StorageBarrels", barrel);
			System.out.println("Hello Server ready.");
			while (true) {
				System.out.print("> ");
				a = sc.nextLine();
				client.print_on_client(a);
			}
		} catch (Exception re) {
			System.out.println("Exception in IndexStorageBarrel.main: " + re);
		} 
	}
}
