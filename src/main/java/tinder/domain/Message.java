package tinder.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Message {
    private final int sender;
    private final int receiver;
    private final String content;
    private Timestamp dateTimeStamp;
    private String dateTimeString;


    public Message(int sender, int receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public Message(int sender, int receiver, String content, Timestamp dateTimeStamp) {
        this(sender, receiver, content);
        this.dateTimeStamp = dateTimeStamp;
        setDateTimeString();
    }

    private void setDateTimeString(){
        LocalDateTime ldt = dateTimeStamp.toInstant().atZone(ZoneId.of("UTC+02:00")).toLocalDateTime();
        dateTimeString = ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm"));

    }


    public String getDateTimeString() {
        return dateTimeString;
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
