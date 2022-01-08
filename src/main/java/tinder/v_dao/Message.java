package tinder.v_dao;

import java.sql.Timestamp;

public class Message {
    private final int sender;
    private final int receiver;
    private final String content;
    private Timestamp dateTimeStamp;

    public Message(int sender, int receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public Message(int sender, int receiver, String content, Timestamp dateTimeStamp) {
        this(sender, receiver, content);
        this.dateTimeStamp = dateTimeStamp;
    }

    public int getSender() {
        return sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTimestamp() {
        return this.dateTimeStamp;
    }
}
