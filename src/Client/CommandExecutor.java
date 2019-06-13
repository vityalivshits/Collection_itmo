package Client;

import Collection.FortressList;
import UTILS.CommandResolver;
import UTILS.FileLoader;
import UTILS.UserCommand;
import UTILS.Message;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

//todo IMPORT
//todo клиент босает все что угодно, а сервер уэе разбирается

public class CommandExecutor {

    private int SERVER_PORT = 8012;
    private SocketAddress serverSocketAddress;
    private DatagramChannel udpChannel;
    private UserCommand userCommand;

    private CommandReader commandReader = new CommandReader();
    private static final CommandResolver commandResolver = new CommandResolver();

    private boolean sent = false;

    public CommandExecutor()  {
        try {
            serverSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), SERVER_PORT);
            udpChannel = DatagramChannel.open();
            udpChannel.connect(serverSocketAddress);
        } catch (UnknownHostException e) {
            System.out.println("Неизвестный хост.");
        } catch (IOException e) {
            System.out.println("Ну и как ты получил I/O exception?");
        }
    }

    void startWork() {

        while(true) {
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
                    System.out.println("Ну и как ты получил I/O exception?");
                } catch (ClassNotFoundException e) {
                    System.out.println("На машине нет необходимого класса.");
                }
            } else {
                System.out.println("Файл не задан.");
            }
        }
        if (CommandResolver.getCOMMANDS().containsKey(userCommand.getName())) {
            Message message = new Message(userCommand);

            try {
                send(message);

                if(sent) {
                    byte[] bytes = receive();
                    if(bytes != null) {
                        Message serverReply = Message.deserialize(bytes);
                        commandResolver.handleReply(userCommand, serverReply);
                    }
                }

            } catch (IOException e) {
                System.out.println("Как ты получил I/O ?");
            } catch (ClassNotFoundException e) {
                System.out.println("На машине нет необходимого класса.");
            }
        } else {
            System.out.println("Неизвестная команда, попробуйте ее раз");
        }
    }

    private void send(Message message) throws IOException {
        byte[] bytes = message.serialize();
        try {
            udpChannel.write(ByteBuffer.wrap(bytes));
            sent = true;
        } catch (PortUnreachableException e) {
            System.out.println("Сервер недоступен");
        }
    }

    private byte[] receive()  {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
        try {
            udpChannel.read(byteBuffer);
            return byteBuffer.array();
        } catch (PortUnreachableException e) {
            System.out.println("Сервер недоступен");
        } catch (IOException e) {
            System.out.println("Ну и как ты этот I/O exception получил??");
        } finally {
            sent = false;
        }
        return null;
    }

    private FortressList clientImport() {
        try {
            String json = FileLoader.getFileContent(userCommand.getArg());
            return new Gson().fromJson(json, FortressList.class);
        } catch (IOException e) {
            System.out.println("Ну и как ты получил этот I/O?");
        }
        System.out.println("Ошибка. Импорт невозможен.");
        return new FortressList();
    }
}
