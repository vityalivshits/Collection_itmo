package Commands;

import Collection.FortressArrayList;
import UTILS.CollectionEntity;
import UTILS.Message;
import UTILS.ParcelContainer;

public class InfoCommand implements Command {
    @Override
    public void clientRun(Message serverReply) {
        ((FortressArrayList) serverReply.getAttachment()).info();
    }

    @Override
    public void serverRun(ParcelContainer container) {
        send(container, new Message(CollectionEntity.getInstance().getCollection()));
    }
}
