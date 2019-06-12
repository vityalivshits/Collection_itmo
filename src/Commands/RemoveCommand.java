package Commands;

import Collection.Fortress;
import UTILS.CollectionEntity;
import UTILS.Message;
import UTILS.ParcelContainer;


public class RemoveCommand implements FortressCommand {
    @Override
    public void operation(Fortress fortress, ParcelContainer container) {
        CollectionEntity
                .getInstance()
                .getCollection()
                .remove(fortress);
        send(container, new Message("Крепость успешно удалена."));
    }
}
