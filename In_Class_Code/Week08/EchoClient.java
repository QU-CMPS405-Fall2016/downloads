import java.net.Socket;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EchoClient {

	public static void main(String[] args) throws Exception {
		// create socket
		int port = 4444;
		Socket clientSocket = new Socket("localhost", port);

		// Create the reader/write
		BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

		// Create the scanner to get input from the user
		Scanner userInput = new Scanner(System.in);

		// Send user input to the server
		while (true) {
			// Get a line of input from the user
			System.out.print("prompt> ");
			String inp = userInput.nextLine();

			// If it is exit, then leave
			if (inp.equals("exit")) {
				break;
			}

			// Send it
			writer.println(inp);

			// Read and print the reply
			String s = reader.readLine();
			System.out.println(s);
		}

		// close IO streams, then socket
		System.err.println("Closing connection with client");
		writer.close();
		reader.close();
		clientSocket.close();
	}
}
