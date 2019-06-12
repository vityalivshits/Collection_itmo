package Commands;

import Collection.Fortress;
import UTILS.CollectionEntity;
import UTILS.Message;
import UTILS.ParcelContainer;

public class AddCommand implements FortressCommand {
    @Override
    public void operation(Fortress fortress, ParcelContainer container) {
        CollectionEntity
                .getInstance()
                .getCollection()
                .add(fortress);
        send(container, new Message("Крепость успешно добавлена."));
    }
}
