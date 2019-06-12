package Commands;

import Collection.Fortress;
import Collection.FortressArrayList;
import UTILS.Message;
import UTILS.ParcelContainer;

import java.io.Serializable;

public interface FortressCommand extends Command {

    @Override
    default void serverRun(ParcelContainer container) {

        if (container.getMessage().attachmentExists()) {
            Serializable attachment = container.getMessage().getAttachment();
            if (attachment instanceof Fortress) {
                operation((Fortress) attachment, container);
            } else {
                send(container, new Message("Неверный формат аргумента"));
            }
        } else {
            send(container, new Message("Аргумент не задан."));
        }
    }

    void operation(Fortress fortress, ParcelContainer container);
}
