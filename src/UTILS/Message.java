package UTILS;

import Collection.Fortress;
import com.google.gson.Gson;

import java.io.*;

public class Message implements Serializable {
    private String header;
    private Serializable attachment;

    public Message(UserCommand userCommand) {
        header = userCommand.getName();

        if(userCommand.argExists()) {
            //todo check if not a fortress
            attachment = new Gson().fromJson(userCommand.getArg(), Fortress.class);
        }
    }

    public Message(String header, Serializable attachment) {
        this.header = header;
        this.attachment = attachment;
    }

    public Message(String s) {
        header = s;
    }

    public Message(Serializable attachment) {
        this.attachment = attachment;
    }

    public String getHeader() {
        return header;
    }

    public void setAttachment(Serializable attachment) {
        this.attachment = attachment;
    }

    public Serializable getAttachment() {
        return attachment;
    }

    public boolean attachmentExists() {
        return attachment != null;
    }

    public byte[] serialize() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        return baos.toByteArray();
    }

    public static Message deserialize(byte[] content) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(content);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Message message = (Message) ois.readObject();
        return message;
    }
}
