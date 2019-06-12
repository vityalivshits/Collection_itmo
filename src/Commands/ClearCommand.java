package Commands;

import UTILS.CollectionEntity;
import UTILS.Message;
import UTILS.ParcelContainer;

public class ClearCommand implements Command {
    @Override
    public void serverRun(ParcelContainer container) {

        CollectionEntity
                .getInstance()
                .getCollection()
                .clear();
        send(container, new Message("Коллекция успешно очищена."));
    }
}
