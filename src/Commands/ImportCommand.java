package Commands;

import Collection.Fortress;
import Collection.FortressArrayList;
import UTILS.CollectionEntity;
import UTILS.Message;
import UTILS.ParcelContainer;

public class ImportCommand implements Command {
    @Override
    public void serverRun(ParcelContainer container) {

        if(container.getMessage().attachmentExists()) {
            Object obj = container.getMessage().getAttachment();
            if(obj instanceof FortressArrayList) {
                FortressArrayList receivedFAL = (FortressArrayList) obj;

                for(Fortress f:receivedFAL.getFortresses()) {
                    CollectionEntity
                            .getInstance()
                            .getCollection()
                            .add(f);
                }
                send(container, new Message("Крепости успешно импортированы в текущую коллекцию"));
            }
            else {
                System.out.println("Неверный формат аргумента.");
            }
        } else {
            send(container, new Message("Аргумент не указан"));
        }
    }
}
