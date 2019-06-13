package Client;

import java.nio.charset.Charset;

public class Client {
    public static void main(String[] args) {

        Charset charset = Charset.forName("UTF-8");

        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.startWork();

    }
}
