import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

class ClientHandler extends Thread {
	Socket clientSocket;

	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void run() {
		// open up IO streams
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

			// waits for data and reads it in until connection dies
			// readLine() blocks until the server receives a new line from
			// client
			String s;
			while ((s = reader.readLine()) != null) {
				writer.println(s);
			}

			// close IO streams, then socket
			System.err.println("Closing connection with client");
			writer.close();
			reader.close();
			
			clientSocket.close();
		} catch (Exception e) {
			System.err.println("Error handling the client");
			return;
		}
	}
}

public class ThreadedEchoServer {

	public static void main(String[] args) throws Exception {

		// create socket
		int port = 4444;
		ServerSocket serverSocket = new ServerSocket(port);
		System.err.println("Started server on port " + port);

		// repeatedly wait for connections, and process
		while (true) {
			// a "blocking" call which waits until a connection is requested
			Socket clientSocket = serverSocket.accept();
			System.err.println("Accepted connection from client");
			ClientHandler ch = new ClientHandler(clientSocket);
			ch.start();
		}
	}
}