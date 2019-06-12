package Commands;

import Collection.Fortress;
import UTILS.CollectionEntity;
import UTILS.Message;
import UTILS.ParcelContainer;

public class RemoveLowerCommand implements FortressCommand {
    @Override
    public void operation(Fortress fortress, ParcelContainer container) {
        CollectionEntity
                .getInstance()
                .getCollection()
                .removeLower(fortress);
        send(container, new Message("Все крепости меньше заданной успешно удалены."));
    }
}
