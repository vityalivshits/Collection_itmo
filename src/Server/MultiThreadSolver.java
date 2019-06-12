package Server;

import UTILS.CommandResolver;
import UTILS.Message;
import UTILS.ParcelContainer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadSolver extends Thread{

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private int SERVER_PORT = 8012;
    private byte[] BUFFER = new byte[8192];
    private DatagramPacket datagramPacket;
    private DatagramSocket udpSocket;
    private Message receivedMessage;

    private InetAddress receivedInetAddress;
    private int receivedPort;

    public MultiThreadSolver() {}

    public void StartListening() {
        datagramPacket = new DatagramPacket(BUFFER, BUFFER.length);

        try {
            udpSocket = new DatagramSocket(SERVER_PORT);
        } catch (SocketException e) {
            System.out.println("Невозможно создать сокет.");
            e.printStackTrace();
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
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Не найден класс. Чтоо?");
            e.printStackTrace();
        }
        return message;
    }
}