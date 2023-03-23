import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.net.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MulticastSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;

public class StorageBarrels extends  UnicastRemoteObject{
    private static final long serialVersionUID = 1L;
    private static final String filepath="C:\\Users\\joanm\\Documents\\Universidade stuff\\3 ano\\2º Semestre\\SD\\Googol\\src\\objFile";
    private String MULTICAST_ADDRESS = "224.3.2.1";
    private int PORT = 4321;

    public StorageBarrels() throws RemoteException {
		super();
	}

    public void readMulticast(){
        MulticastSocket socket = null;
        try {
            socket = new MulticastSocket(PORT);  // create socket and bind it
            InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
            
            socket.joinGroup(group);
            while (true) {
                byte[] buffer = new byte[256];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                System.out.println("Received packet from " + packet.getAddress().getHostAddress() + ":" + packet.getPort() + " with message:");
                String message = new String(packet.getData(), 0, packet.getLength());
             
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    
    public Object  readFromObject() throws RemoteException {
        try{

		
        FileInputStream fileIn = new FileInputStream(filepath);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);

        Object obj = objectIn.readObject();

        System.out.println("The Object has been read from the file");
        objectIn.close();
        return obj;

    } catch (Exception ex) {
        ex.printStackTrace();
        return null;
    }
	}


    public static void main(String[] args) throws Exception {
        System.getProperties().put("java.security.policy","security.policy");
  
        try {
			StorageBarrels s = new StorageBarrels();
			Naming.rebind("rmi://localhost/StorageBarrels", s);
			System.out.println("Storage Barrels server ready.");
		} catch (RemoteException re) {
			System.out.println("Exception in StorageBarrels.main: " + re);
    }
}
}