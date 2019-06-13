package Commands;

import Collection.FortressComparator;
import Collection.FortressList;
import UTILS.CollectionEntity;
import UTILS.Message;
import UTILS.ParcelContainer;

public class ShowCommand implements Command {
    @Override
    public void clientRun(Message serverReply) {
        ((FortressList) serverReply.getAttachment()).show();
    }

    @Override
    public void serverRun(ParcelContainer container) {
        CollectionEntity.getInstance().getCollection().getFortresses().sort(new FortressComparator());
        send(container, new Message(CollectionEntity.getInstance().getCollection()));
    }
}
