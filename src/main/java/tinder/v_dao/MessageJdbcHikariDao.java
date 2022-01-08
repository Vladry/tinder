package tinder.v_dao;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;

public class MessageJdbcHikariDao implements MessageDao_v {
    public final HikariDataSource hikariSource;

    public MessageJdbcHikariDao(){
        hikariSource = new HikariDataSource();
        hikariSource.setDriverClassName("org.postgresql.ds.PGSimpleDataSource");
        hikariSource.setJdbcUrl("jdbc:postgresql://ec2-52-86-177-34.compute-1.amazonaws.com:5432/d7g10jrgsjruk4");
        hikariSource.setUsername("mtmaprkfztrfne");
        hikariSource.setPassword("d727d367387272970efb9ca62ff523bb77695ebf5f9a7e7b83af48e216e2fb64");
        hikariSource.setMinimumIdle(1);
        hikariSource.setMaximumPoolSize(10);
    }

    @Override
    public void createMessage(Message msg) {
        try(Connection connection = hikariSource.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(
                    "INSERT INTO \"v_messages\" (sender, receiver, content) VALUES ( ?, ?, ? );");
            pst.setInt(1, msg.getSender());
            pst.setInt(2, msg.getReceiver());
            pst.setString(3, msg.getContent());
            int rows = pst.executeUpdate();

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Message> retrieveAllMessages(int sender, int receiver) {
        ArrayList<Message> messages = new ArrayList<>();
        try(Connection con = hikariSource.getConnection()){
            PreparedStatement pst = con.prepareStatement(
                    "SELECT * FROM \"v_messages\" WHERE sender = ? AND receiver = ? ORDER BY dateTimeStamp;");
            pst.setInt(1, sender);
            pst.setInt(2, receiver);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                String content = rs.getString("content");
                Timestamp dateTimeStamp = rs.getTimestamp("dateTimeStamp");
                Message msg = new Message(sender, receiver, content, dateTimeStamp);
                messages.add(msg);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }
}
