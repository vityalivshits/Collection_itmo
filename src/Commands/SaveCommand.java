package Commands;

import UTILS.CollectionEntity;
import UTILS.Message;
import UTILS.ParcelContainer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveCommand implements Command {

    @Override
    public void serverRun(ParcelContainer container) {
        String json;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        json = gson.toJson(CollectionEntity.getInstance().getCollection());

        try {
            FileOutputStream fos = new FileOutputStream("fortresses.json");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            byte[] buffer = json.getBytes();
            bos.write(buffer);
            bos.flush();
            send(container, new Message("Изменения успешно сохранены."));

        } catch (FileNotFoundException e) {
            send(container, new Message("Файл fortresses.json не был найден."));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
