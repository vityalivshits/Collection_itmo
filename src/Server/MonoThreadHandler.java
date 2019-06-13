package Server;

import UTILS.CommandResolver;
import UTILS.ParcelContainer;

public class MonoThreadHandler implements Runnable{

    private ParcelContainer container;
    private static final CommandResolver commandResolver = new CommandResolver();

    public MonoThreadHandler(ParcelContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        handleCommand(container);
    }

    private void handleCommand(ParcelContainer parcelContainer) {
        commandResolver.execute(parcelContainer);
    }
}
