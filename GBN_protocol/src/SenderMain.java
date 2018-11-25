import java.net.*;
import java.util.Scanner;
import java.io.*;

public class SenderMain {

	public static void main(String [] args) throws Exception{
		
		Scanner myScanner = new Scanner(System.in);
		
		System.out.print("Enter port number: ");
		int portNo = myScanner.nextInt();
		
		System.out.print("Enter window size: ");
		int windowSize = myScanner.nextInt();
		
		System.out.print("Enter data to send: ");
		String data = myScanner.nextLine();
		
		try {
	         System.out.println("Connecting to server on port " + portNo);
	         
	         Socket client = new Socket("server", portNo);
	         System.out.println("Just connected to " + client.getRemoteSocketAddress());
	         
	         Sender sender = new Sender(windowSize, data, Socket);
	         
//	         OutputStream outToServer = client.getOutputStream();
//	         DataOutputStream out = new DataOutputStream(outToServer);
//	         
//	         out.writeUTF("Hello from " + client.getLocalSocketAddress());
//	         InputStream inFromServer = client.getInputStream();
//	         DataInputStream in = new DataInputStream(inFromServer);
//	         
//	         System.out.println("Server says " + in.readUTF());
//	         client.close();
	         
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
		
	}
	
}
