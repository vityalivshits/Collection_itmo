package UTILS;

import Collection.FortressList;
import com.google.gson.Gson;

import java.io.IOException;

public class CollectionEntity {
    private static CollectionEntity instance;

    private FortressList collection;

    private CollectionEntity() {
        collection = basicLoad();
    }

    public void setCollection(FortressList collection) {
        this.collection = collection;
    }

    public FortressList getCollection() {
        return collection;
    }

    public static CollectionEntity getInstance() {
        if(instance == null) {
            instance = new CollectionEntity();
        }
        return instance;
    }

    private FortressList basicLoad() {

        String json = null;
        try {
            json = FileLoader.getFileContent("fortresses.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FortressList newFAL = new Gson().fromJson(json, FortressList.class);
        System.out.println(("Загрузка состояния успешно проведена."));
        return newFAL;
    }
}
