import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPEchoServer {

	public static void main(String[] args) {
		DatagramSocket server = null;

		// Create the UDP socket
		try {
			server = new DatagramSocket(4444);
		} catch (SocketException socketException) {
			System.exit(1);
		}

		while (true) {
			try {
				// Receive a UDP packet
				byte[] data = new byte[1024];
				DatagramPacket packet = new DatagramPacket(data, data.length);
				server.receive(packet);

				// Print out info about the received packet
				System.out.printf("Received packet from client %s:%s with length %s with the body:\n%s\n",
						packet.getAddress(), packet.getPort(), packet.getLength(),
						new String(packet.getData(), 0, packet.getLength()));

				// Send the packet back. (Echo)
				DatagramPacket sendPacket = new DatagramPacket(packet.getData(), packet.getLength(),
						packet.getAddress(), packet.getPort());
				server.send(sendPacket);
			} catch (Exception e) {
				break;
			}
		}
		server.close();
	}
}
