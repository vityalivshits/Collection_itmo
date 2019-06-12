package UTILS;

import Commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandResolver {
    private static final Map<String, Command> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("help", new HelpCommand());
        COMMANDS.put("info", new InfoCommand());
        COMMANDS.put("show", new ShowCommand());
        COMMANDS.put("add", new AddCommand());
        COMMANDS.put("remove", new RemoveCommand());
        COMMANDS.put("remove_lower", new RemoveLowerCommand());
        COMMANDS.put("remove_all", new RemoveAllCommand());
        COMMANDS.put("load", new LoadCommand());
        COMMANDS.put("save", new SaveCommand());
        COMMANDS.put("import", new ImportCommand());
        COMMANDS.put("clear", new ClearCommand());
    }

    public static Map<String, Command> getCOMMANDS() {
        return COMMANDS;
    }

    public void execute(ParcelContainer parcelContainer) {
        Command command = COMMANDS.get(parcelContainer.getMessage().getHeader());
        command.serverRun(parcelContainer);
    }

    public void handleReply(UserCommand userCommand, Message serverReply) {
        Command command = COMMANDS.get(userCommand.getName());
        command.clientRun(serverReply);
    }
}
