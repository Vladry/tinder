package tinder.v_dao;

import java.util.ArrayList;

public interface MessageDao_v {
    void createMessage(Message msg);

    ArrayList<Message>  retrieveAllMessages(int sender, int receiver);
}
