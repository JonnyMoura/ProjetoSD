import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.Scanner;
import java.io.Serializable;




public class RMIClient extends UnicastRemoteObject implements SearchModule, Serializable {
    public RMIClient() throws RemoteException {
        super();
    }

    public void print_on_client(String s) throws RemoteException {
        System.out.println("> " + s);
    }

    public static void main(String args[]) {
        String a;

        try (Scanner sc = new Scanner(System.in)) {
			StorageBarrels barrel = (StorageBarrels) Naming.lookup("StorageBarrels");
			RMIClient c = new RMIClient();
			barrel.subscribe(args[0], (SearchModule) c);
			System.out.println("Client sent subscription to server");
			while (true) {
				System.out.print("> ");
				a = sc.nextLine();
				barrel.print_on_server(a);
			}

		} catch (Exception e) {
			System.out.println("Exception in main: " + e);
		}

        /*
        while(true) {
            String input;
            System.out.println("Intruduza a sua pesquisa:");
            try (Scanner sc = new Scanner(System.in)) {
                input = sc.nextLine();
            } catch (Exception e) {
                System.out.println("Exception in main: " + e);
            }
            
        }
        */
    }

}
