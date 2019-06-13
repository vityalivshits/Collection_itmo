package Server;

import UTILS.Message;
import UTILS.ParcelContainer;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadSolver {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    private final int SERVER_PORT;
    private byte[] BUFFER = new byte[8192];
    private DatagramPacket datagramPacket;
    private DatagramSocket udpSocket;
    private Message receivedMessage;

    private InetAddress receivedInetAddress;
    private int receivedPort;

    public MultiThreadSolver(int port) {
        SERVER_PORT = port;
    }

    public void StartListening() {
        datagramPacket = new DatagramPacket(BUFFER, BUFFER.length);

        try {
            udpSocket = new DatagramSocket(SERVER_PORT);
        } catch (SocketException e) {
            System.out.println("Невозможно создать сокет.");
            System.exit(0);
        }

        while (true) {
            receivedMessage = receiveMessage();

            ParcelContainer parcelContainer = new ParcelContainer(receivedMessage, udpSocket, receivedInetAddress, receivedPort);
            executorService.execute(new MonoThreadHandler(parcelContainer));
        }
    }

    private Message receiveMessage() {
        Message message = null;
        try {
            udpSocket.receive(datagramPacket);
            receivedInetAddress = datagramPacket.getAddress();
            receivedPort = datagramPacket.getPort();
            message = Message.deserialize(datagramPacket.getData());

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода.");
        } catch (ClassNotFoundException e) {
            System.out.println("Не найден класс. Чтоо?");
        }
        return message;
    }
}