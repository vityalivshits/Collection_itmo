package Client;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите порт(целое число от 0 до 65535): ");
        try {
            int port = Integer.parseInt(scanner.nextLine());
            CommandExecutor commandExecutor = new CommandExecutor(port);
            commandExecutor.startWork();
        } catch (NumberFormatException e) {
            System.err.println("Порт должен быть целым числом от 0 до 65535, клиент будет отключен.");
            System.exit(0);
        }

    }
}
