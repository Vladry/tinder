package vlad.dao;

//import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.postgresql.ds.PGPoolingDataSource;

import java.sql.*;
import java.util.List;

public class UserJdbcDao implements UserDao {
//    private MysqlConnectionPoolDataSource source;
    private PGPoolingDataSource source;

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
        source = new PGPoolingDataSource();
        source.setServerName("ec2-44-193-111-218.compute-1.amazonaws.com");
        source.setDatabaseName("d6bdpvuaqdavsb");
        source.setUser("pftdgngxtooerk");
        source.setPassword("72e03083f006bf5eb24f445f08ebf50254ff3af2f16aeaec8b2df2b08353412f");
        source.setMaxConnections(10);
    }

    @Override
    public boolean create(User user) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users.users VALUES (name = ?, age = ?, groupId = ?, login = ?, password = ?)");
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id = 3");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setLong(2, user.getAge());
            preparedStatement.setLong(3, user.getGroupId());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());


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
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users.users WHERE id = ?");
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id = 3");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString(2);
                int age = resultSet.getInt("age");
                Long groupId = resultSet.getLong("group_id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                return new User(id, name, age, groupId, login, password);
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
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
    @Override
    public User findByLoginPass(String loginUser, String passwordUser) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users.users WHERE login=? AND password=?");
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id = 3");
            preparedStatement.setString(1, loginUser);
            preparedStatement.setString(2, passwordUser);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString(2);
                int age = resultSet.getInt("age");
                Long groupId = resultSet.getLong("group_id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                return new User(id, name, age, groupId, login, password);
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
}
