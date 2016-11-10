import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class UDPEchoClient {

	public static void main(String[] args) throws Exception {

		// create socket
		int port = 4444;

		DatagramSocket clientSocket = new DatagramSocket();

		// Create the scanner to get input from the user
		Scanner userInput = new Scanner(System.in);

		// repeatedly wait for connections, and process
		while (true) {

			// Get a line of input from the user
			System.out.print("prompt> ");
			String inp = userInput.nextLine();

			// If it is exit, then leave
			if (inp.equals("exit")) {
				break;
			}

			// Send it
			byte[] data = inp.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), port);
			clientSocket.send(packet);

			// Receive the reply
			byte[] message = new byte[1024];
			DatagramPacket receivedPacket = new DatagramPacket(message, message.length);
			clientSocket.receive(receivedPacket);

			// Read and print the reply
			String s = new String(receivedPacket.getData());
			System.out.println(s);
		}

		// close the socket
		System.err.println("Closing connection with client");
		clientSocket.close();
	}
}