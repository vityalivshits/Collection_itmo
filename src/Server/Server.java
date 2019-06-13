package Server;

import java.util.Scanner;

public class Server {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите порт(целое число от 0 до 65535): ");
        try {
            int port = Integer.parseInt(scanner.nextLine());
            MultiThreadSolver multiThreadSolver = new MultiThreadSolver(port);
            multiThreadSolver.StartListening();
        } catch (NumberFormatException e) {
            System.err.println("Порт должен быть целым числом от 0 до 65535, клиент будет отключен.");
            System.exit(0);
        }

    }
}
