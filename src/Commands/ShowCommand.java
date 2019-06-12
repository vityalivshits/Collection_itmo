package Commands;

import Collection.FortressArrayList;
import UTILS.CollectionEntity;
import UTILS.Message;
import UTILS.ParcelContainer;

public class ShowCommand implements Command {
    @Override
    public void clientRun(Message serverReply) {
        ((FortressArrayList) serverReply.getAttachment()).show();
    }

    @Override
    public void serverRun(ParcelContainer container) {
        send(container, new Message(CollectionEntity.getInstance().getCollection()));
    }
}
