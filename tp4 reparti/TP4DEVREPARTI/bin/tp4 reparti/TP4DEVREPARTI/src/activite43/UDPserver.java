package activite43;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class UDPserver {
    private static final int SERVER_PORT = 9129;
    private static final List<InetSocketAddress> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // Création d'une instance de DatagramSocket pour écouter sur le port spécifié
            DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT);

            System.out.println("Serveur UDP en écoute sur le port " + SERVER_PORT);

            while (true) {
                // Initialisation d'un tableau de bytes pour recevoir les données du client
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                // Réception des données du client
                serverSocket.receive(receivePacket);

                // Conversion des données reçues en une chaîne de caractères
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetSocketAddress clientAddress = new InetSocketAddress(receivePacket.getAddress(), receivePacket.getPort());

                // Vérification si le message est une demande de rejoindre la discussion
                if (message.startsWith("/join")) {
                    // Nouveau client rejoint la discussion
                    clients.add(clientAddress);
                    System.out.println("Nouveau client rejoint : " + clientAddress);
                } else {
                    // Diffuser le message à tous les autres clients
                    broadcastMessage(message, clientAddress);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fonction pour diffuser un message à tous les clients, sauf l'expéditeur
    private static void broadcastMessage(String message, InetSocketAddress sender) {
        for (InetSocketAddress client : clients) {
            if (!client.equals(sender)) {
                sendMessage(message, client);
            }
        }
    }

    // Fonction pour envoyer un message à un client spécifique
    private static void sendMessage(String message, InetSocketAddress client) {
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, client.getAddress(), client.getPort());
            socket.send(sendPacket);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
