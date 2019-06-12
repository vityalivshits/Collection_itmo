package Client;

import Collection.FortressArrayList;
import UTILS.CommandResolver;
import UTILS.FileLoader;
import UTILS.UserCommand;
import UTILS.Message;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

//todo IMPORT
//todo клиент босает все что угодно, а сервер уэе разбирается

public class CommandExecutor {

    private int SERVER_PORT = 8012;
    private SocketAddress serverSocketAdress;
    private DatagramChannel udpChannel;
    private UserCommand userCommand;

    private CommandReader commandReader = new CommandReader();
    private static final CommandResolver commandResolver = new CommandResolver();

    public CommandExecutor() throws UnknownHostException, IOException {
        serverSocketAdress = new InetSocketAddress(InetAddress.getLocalHost(), SERVER_PORT);
        udpChannel = DatagramChannel.open();
        udpChannel.connect(serverSocketAdress);
    }

    void startWork() {

        while (true) {
            inviting();
            userCommand = commandReader.readCommand();
            processCommand(userCommand);

        }
    }

    private void inviting() {
        System.out.print("Введите команду >>> ");
    }

    private void processCommand(UserCommand userCommand) {
        if (userCommand.getName().equals("import")) {
            if (userCommand.argExists()) {
                Message message = new Message(userCommand.getName());
                message.setAttachment(clientImport());
                try {
                    send(message);
                    Message serverReply = Message.deserialize(receive());
                    commandResolver.handleReply(userCommand, serverReply);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Файл не задан.");
            }
        }
        if (CommandResolver.getCOMMANDS().containsKey(userCommand.getName())) {
            Message message = new Message(userCommand);

            try {
                send(message);

                Message serverReply = Message.deserialize(receive());
                commandResolver.handleReply(userCommand, serverReply);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Неизвестная команда, попробуйте ее раз");
        }
    }

    private void send(Message message) throws IOException {
        byte[] bytes = message.serialize();
        udpChannel.write(ByteBuffer.wrap(bytes));
    }

    private byte[] receive() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
        udpChannel.read(byteBuffer);
        return byteBuffer.array();
    }

    private FortressArrayList clientImport() {
        try {
            String json = FileLoader.getFileContent(userCommand.getArg());
            return new Gson().fromJson(json, FortressArrayList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Ошибка. Импорт невозможен.");
        return new FortressArrayList();
    }
}
