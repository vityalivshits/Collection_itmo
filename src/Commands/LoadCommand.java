package Commands;

import Collection.FortressList;
import UTILS.CollectionEntity;
import UTILS.FileLoader;
import UTILS.Message;
import UTILS.ParcelContainer;
import com.google.gson.Gson;

import java.io.IOException;

public class LoadCommand implements Command {
    @Override
    public void serverRun(ParcelContainer container) {

        if(container.getMessage().attachmentExists()) {
            try {
                String json = FileLoader.getFileContent((String) container.getMessage().getAttachment());
                FortressList newFAL = new Gson().fromJson(json, FortressList.class);
                CollectionEntity.getInstance().setCollection(newFAL);
                send(container, new Message("Загрузка состояния успешно проведена."));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            send(container, new Message("Файл не указан."));
        }
    }
}
