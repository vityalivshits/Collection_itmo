package Client;

import UTILS.UserCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandReader {

    private UserCommand userCommand;

    public CommandReader() {
        userCommand = new UserCommand();
    }

    UserCommand readCommand() {
        String command;
        try {
            while ((command = readNextCommand()) != null) {
                command = command.replace("\\s{2,}", " ").trim();
                if(command.equals("quit")) {
                    System.out.println("Сессия закончена. Хорошего дня!");
                    System.exit(0);
                }
                if (command.isEmpty()) {
                    System.out.println("Введите команду.");
                }

                int spaceIndex = command.indexOf(" ");
                if (spaceIndex == -1) {
                    this.userCommand.setName(command);
                    this.userCommand.setArg(null);
                } else {
                    String name = command.substring(0, spaceIndex);
                    String arg = command.substring(spaceIndex + 1);
                    this.userCommand.setName(name);
                    this.userCommand.setArg(arg);
                }
                return this.userCommand;
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода.");
        }
        return this.userCommand;
    }

    private String readNextCommand() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        char running;
        boolean inString = false;
        do {
            int current = reader.read();
            if (current == -1) {
                return null;
            }
            running = (char) current;

            if (running != ';' || inString) {
                builder.append(running);
            }
            if (running == '"') {
                inString = !inString;
            }
        } while (running != ';' || inString);
        return builder.toString();
    }
}
