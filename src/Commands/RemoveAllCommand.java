package Commands;

import Collection.Fortress;
import UTILS.CollectionEntity;
import UTILS.Message;
import UTILS.ParcelContainer;

public class RemoveAllCommand implements FortressCommand{
    @Override
    public void operation(Fortress fortress, ParcelContainer container) {
        CollectionEntity.getInstance()
                .getCollection()
                .removeAll(fortress);
        send(container, new Message("Все крепости, эквивалентные данной, удалены."));
    }
}
