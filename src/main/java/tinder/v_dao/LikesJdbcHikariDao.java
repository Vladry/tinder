package tinder.v_dao;

import com.zaxxer.hikari.HikariDataSource;
import tinder.dao.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LikesJdbcHikariDao implements LikesDao_v {
    private final HikariDataSource hikariSource;

    public LikesJdbcHikariDao() {
        hikariSource = new HikariDataSource();
        hikariSource.setDriverClassName("org.postgresql.ds.PGSimpleDataSource");
        hikariSource.setJdbcUrl("jdbc:postgresql://ec2-52-86-177-34.compute-1.amazonaws.com:5432/d7g10jrgsjruk4");
        hikariSource.setUsername("mtmaprkfztrfne");
        hikariSource.setPassword("d727d367387272970efb9ca62ff523bb77695ebf5f9a7e7b83af48e216e2fb64");
        hikariSource.setMinimumIdle(1);
        hikariSource.setMaximumPoolSize(10);
    }

    @Override
    public void processLiked(int loggedInId, int candidateId, boolean verdict) {
        try (Connection connection = hikariSource.getConnection()) {
            PreparedStatement selReq = connection.prepareStatement(
                    "SELECT * FROM \"v_liked\" WHERE who_id = ? AND whom_id = ?;");
            selReq.setInt(1, loggedInId);
            selReq.setInt(2, candidateId);
            ResultSet res = selReq.executeQuery();
            PreparedStatement createReq = null;
            if (res.isBeforeFirst()) {
                createReq = connection.prepareStatement(
                        "UPDATE \"v_liked\" SET islike = ? WHERE who_id = ? AND whom_id = ?;");
            } else {
                createReq = connection.prepareStatement(
                        "INSERT INTO \"v_liked\" (isLike, who_id, whom_id) VALUES(?, ?, ?);");
            }
            createReq.setBoolean(1, verdict);
            createReq.setInt(2, loggedInId);
            createReq.setInt(3, candidateId);
            int rawsAffected = createReq.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Integer> retrieveAllLiked(int loggedInId) {
        ArrayList<Integer> idList = new ArrayList<>();
        try (Connection connection = hikariSource.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(
                    "SELECT whom_id from \"v_liked\" WHERE who_id = ? AND isLike = true;");
            pst.setInt(1, loggedInId);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                idList.add(res.getInt("whom_id"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return idList;
    }


    public ArrayList<User> retrieveLikedUsers(int loggedInId) {
        ArrayList<User> contactList = new ArrayList<>();
        try (Connection connection = hikariSource.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(
                    "SELECT * FROM \"v_users\" WHERE id IN" +
                            "(SELECT whom_id from \"v_liked\" WHERE who_id = ? AND isLike = true) ");
            pst.setInt(1, loggedInId);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                User contact = new User(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getInt("age"),
                        res.getString("url_photo"),
                        res.getTimestamp("last_visit_date_time")
                );
                contactList.add(contact);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contactList;
    }

}
