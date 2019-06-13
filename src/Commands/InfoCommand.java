package Commands;

import Collection.FortressComparator;
import Collection.FortressList;
import UTILS.CollectionEntity;
import UTILS.Message;
import UTILS.ParcelContainer;

public class InfoCommand implements Command {
    @Override
    public void clientRun(Message serverReply) {
        ((FortressList) serverReply.getAttachment()).info();
    }

    @Override
    public void serverRun(ParcelContainer container) {
        send(container, new Message(CollectionEntity.getInstance().getCollection()));

    }
}
