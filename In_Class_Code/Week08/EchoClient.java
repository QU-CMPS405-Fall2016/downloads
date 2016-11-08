/******************************************************************************
 *  Compilation:  javac EchoServer.java
 *  Execution:    java EchoServer port
 *  Dependencies: In.java Out.java
 *  
 *  Runs an echo server which listents for connections on port 4444,
 *  and echoes back whatever is sent to it.
 *
 *
 *  % java EchoServer 4444
 *
 *
 *  Limitations
 *  -----------
 *  The server is not multi-threaded, so at most one client can connect
 *  at a time.
 *  
 *  Courtesy of http://introcs.cs.princeton.edu/java/84network/EchoServer.java
 *
 ******************************************************************************/

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