package tinder.v_dao;

import com.zaxxer.hikari.HikariDataSource;
import tinder.dao.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserJdbcHikariDao implements UserDao_v {
    private final HikariDataSource hikariSource;

    public UserJdbcHikariDao() {
        hikariSource = new HikariDataSource();
        hikariSource.setDriverClassName("org.postgresql.ds.PGSimpleDataSource");
        hikariSource.setJdbcUrl("jdbc:postgresql://ec2-52-86-177-34.compute-1.amazonaws.com:5432/d7g10jrgsjruk4");
        hikariSource.setUsername("mtmaprkfztrfne");
        hikariSource.setPassword("d727d367387272970efb9ca62ff523bb77695ebf5f9a7e7b83af48e216e2fb64");
        hikariSource.setMinimumIdle(1);
        hikariSource.setMaximumPoolSize(10);
    }


    @Override
    public int create(String email, String password, String name, int age, String urlPhoto) {
        Connection connection = null;
        try {
            connection = hikariSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement st = connection.prepareStatement(
                    "INSERT INTO \"v_users\" (email, password, name, age, url_photo) " +
                            "VALUES (?, ?, ?, ?, ?); ");
            st.setString(1, email);
            st.setString(2, password);
            st.setString(3, name);
            st.setInt(4, age);
            st.setString(5, urlPhoto);
            int rows = st.executeUpdate();
            connection.commit();
            return rows;

        } catch (SQLException e) {
            System.out.println("error creating a user");
            System.out.println(e.getMessage());
            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException er) {
                System.out.println("error rolling back connection");
                System.out.println(er.getMessage());
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return 0;
    }

    @Override
    public User retrieveByEmailPassword(String email, String password) {
        Connection connection = null;
        try {
            connection = hikariSource.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM \"v_users\" " +
                    "WHERE email = ? AND password = ? ");
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.isBeforeFirst()) {
                rs.next();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String urlPhoto = rs.getString("url_photo");
                return new User(id, email, password, name, age, urlPhoto);

            } else {
                System.out.println("no user found by provided email and password !");
                return null;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException er) {
                    System.out.println(er.getMessage());
                }
            }
        }

        return null;
    }

    @Override
    public User retrieveById(int id) {
        Connection connection = null;
        try {
            connection = hikariSource.getConnection();
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM \"v_users\" WHERE id = ? ");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.isBeforeFirst()) {
                rs.next();
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String urlPhoto = rs.getString("url_photo");
                return new User(id, email, password, name, age, urlPhoto);

            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException er) {
                    System.out.println(er.getMessage());
                }
            }
        }

        return null;

    }

    @Override
    public User retrieveByIsLiked(int id) {
        return null;
    }
}
