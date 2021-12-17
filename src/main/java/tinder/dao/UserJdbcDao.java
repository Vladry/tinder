package tinder.dao;

//import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao implements UserDao {
//    private MysqlConnectionPoolDataSource source;
//    private PGPoolingDataSource source;
    private HikariDataSource source;
    List<User> allUsers = new ArrayList<>();

/*    public UserJdbcDao() {
        try {
            source = new MysqlConnectionPoolDataSource();
            source.setServerName("localhost");
            source.setPort(3306);
            source.setDatabaseName("users");
            source.setUser("root");
            source.setPassword("root");
            source.setAllowMultiQueries(true);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/

    public UserJdbcDao() {
        source = new HikariDataSource();
        source.setDriverClassName("org.postgresql.ds.PGSimpleDataSource");
            source.setJdbcUrl("jdbc:postgresql://ec2-52-86-177-34.compute-1.amazonaws.com:5432/d7g10jrgsjruk4");
//            source.setJdbcUrl(System.getenv("JDBC_DATABASE_URL"));
            source.setUsername("mtmaprkfztrfne");
            source.setPassword("d727d367387272970efb9ca62ff523bb77695ebf5f9a7e7b83af48e216e2fb64");
            source.setMinimumIdle(1);
            source.setMaximumPoolSize(3);
//            source.setSchema("d7g10jrgsjruk4");
//            source.addDataSourceProperty("databaseName", "d7g10jrgsjruk4");
//            source.addDataSourceProperty("serverName", "ec2-52-86-177-34.compute-1.amazonaws.com");
//            source.addDataSourceProperty("serverPort", "5432");


//        source = new PGPoolingDataSource();
//        source.setServerName("ec2-52-86-177-34.compute-1.amazonaws.com");
//        source.setDatabaseName("d7g10jrgsjruk4");
//        source.setUser("mtmaprkfztrfne");
//        source.setPassword("d727d367387272970efb9ca62ff523bb77695ebf5f9a7e7b83af48e216e2fb64");
//        source.setMaxConnections(10);
    }

    @Override
    public boolean create(User user) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO \"appUsers\".\"appUsers\"(id,email,password,name,age) VALUES (?,?,?,?,?)");

            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setLong(5, user.getAge());

            int executionResult = preparedStatement.executeUpdate();
            connection.commit();

            return executionResult > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public User read(Long userId) {
        Connection connection = null;
        try {
            connection = source.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"appUsers\".\"appUsers\" WHERE id = ?");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                return new User(id, email, password, name, age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public boolean delete(long id) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM \"appUsers\".\"appUsers\" WHERE id=?");
            preparedStatement.setLong(1, id);

            int executionResult = preparedStatement.executeUpdate();
//            connection.commit();

            return executionResult > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        Connection connection = null;
        try {
            connection = source.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"appUsers\".\"appUsers\"");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                allUsers.add(new User(id, email, password, name, age));
//                System.out.printf("%d \t %s\t %s\t %s\t %d\n",id, email, password, name, age);
            }
            return allUsers;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public User findByLoginPass(String loginUser, String passwordUser) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"appUsers\".\"appUsers\" WHERE email=? AND password=?");
            preparedStatement.setString(1, loginUser);
            preparedStatement.setString(2, passwordUser);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                return new User(id, email, password, name, age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void deleteAllProbationaryUsers() {
        Connection connection = null;
        try {
            connection = source.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM \"appUsers\".\"appUsers\" WHERE id BETWEEN 100L AND 1000L");
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQL error");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("SQL error at closing connection");
                }
            }
        }
    }

    ;

}

