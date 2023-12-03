package activite43;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UDPclient {
    private static final int SERVER_PORT = 9129;

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez votre nom d'utilisateur : ");
            String username = scanner.nextLine();

            // Création d'une instance de DatagramSocket pour le client
            DatagramSocket clientSocket = new DatagramSocket();

            // Envoi du message au serveur pour rejoindre la discussion
            String joinMessage = "/join " + username;
            sendMessage(joinMessage, new InetSocketAddress("localhost", SERVER_PORT));

            // Démarrer un thread pour recevoir les messages du serveur
            Thread receiveThread = new Thread(() -> {
                while (true) {
                    receiveMessage(clientSocket);
                }
            });
            receiveThread.start();

            // Lire les messages depuis la console et les envoyer au serveur
            while (true) {
                String userMessage = scanner.nextLine();
                String fullMessage = username + ": " + userMessage;
                sendMessage(fullMessage, new InetSocketAddress("localhost", SERVER_PORT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fonction pour envoyer un message au serveur
    private static void sendMessage(String message, InetSocketAddress serverAddress) {
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress.getAddress(), serverAddress.getPort());
            socket.send(sendPacket);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fonction pour recevoir un message du serveur
    private static void receiveMessage(DatagramSocket socket) {
        try {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println(receivedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
