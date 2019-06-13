package Commands;

import UTILS.Message;
import UTILS.ParcelContainer;

import java.io.IOException;
import java.net.DatagramPacket;

public interface Command {

    void serverRun(ParcelContainer container);

    default void clientRun(Message serverReply) {
        System.out.println(serverReply.getHeader());
    }

    default void send(ParcelContainer container, Message messageToSend) {
        try {
            byte[] bytes = messageToSend.serialize();
            container.getUdpSocket().send(new DatagramPacket(bytes, bytes.length, container.getAddress(), container.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
