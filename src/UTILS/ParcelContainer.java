package UTILS;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class ParcelContainer {
    private Message message;
    private DatagramSocket udpSocket;
    private InetAddress address;
    private int port;

    public ParcelContainer(Message message, DatagramSocket udpSocket, InetAddress address, int port) {
        this.message = message;
        this.udpSocket = udpSocket;
        this.address = address;
        this.port = port;
    }

    public Message getMessage() {
        return message;
    }

    public DatagramSocket getUdpSocket() {
        return udpSocket;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
