package UTILS;

import Collection.FortressArrayList;
import com.google.gson.Gson;

import java.io.IOException;

public class CollectionEntity {
    private static CollectionEntity instance;

    private FortressArrayList collection;

    private CollectionEntity() {
        collection = basicLoad();
    }

    public void setCollection(FortressArrayList collection) {
        this.collection = collection;
    }

    public FortressArrayList getCollection() {
        return collection;
    }

    public static CollectionEntity getInstance() {
        return instance;
    }

    private FortressArrayList basicLoad() {

        String json = null;
        try {
            json = FileLoader.getFileContent("fortresses.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FortressArrayList newFAL = new Gson().fromJson(json, FortressArrayList.class);
        System.out.println(("Загрузка состояния успешно проведена."));
        return newFAL;
    }
}
