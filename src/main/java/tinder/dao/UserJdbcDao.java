package tinder.dao;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class UserJdbcDao implements UserDao {

        private final HikariDataSource source;
        List<User> allUsers = new ArrayList<>();

        public UserJdbcDao() {
            source = new HikariDataSource();
            source.setDriverClassName("org.postgresql.ds.PGSimpleDataSource");
            source.setJdbcUrl("jdbc:postgresql://ec2-52-86-177-34.compute-1.amazonaws.com:5432/d7g10jrgsjruk4");
            source.setUsername("mtmaprkfztrfne");
            source.setPassword("d727d367387272970efb9ca62ff523bb77695ebf5f9a7e7b83af48e216e2fb64");
            source.setMinimumIdle(1);
            source.setMaximumPoolSize(3);
        }


    @Override
    public boolean create(User user) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO \"appUsers\".\"appUsers\"(id,email,password,name,age,url_photo) VALUES (?,?,?,?,?,?)");

            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setLong(5, user.getAge());
            preparedStatement.setString(6, user.getUrlPhoto());

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
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM \"appUsers\".\"appUsers\" WHERE id = ?");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String urlPhoto = resultSet.getString("url_photo");
                return new User(id, email, password, name, age,urlPhoto);
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
    public boolean update(User user) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update \"appUsers\".\"appUsers\"\n" +
                            "set email = ?,password = ?,name = ?,age = ?,url_photo = ?,last_date=?\n" +
                            "where id = ?");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setLong(4, user.getAge());
            preparedStatement.setString(5, user.getUrlPhoto());
            java.sql.Date sqlDate = new java.sql.Date(user.getLoginDate().getTime());

            preparedStatement.setDate(6, sqlDate);
            preparedStatement.setLong(7, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("who_id");
                System.out.println(id);
                return id > 0;
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
        return false;

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
                String url_photo = resultSet.getString("url_photo");
                allUsers.add(new User(id, email, password, name, age,url_photo));
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
                String url_photo = resultSet.getString("url_photo");
                return new User(id, email, password, name, age,url_photo);
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
    public int findNumRaws() {
        Connection connection = null;
        try {
            connection = source.getConnection();
            Statement statement = connection.createStatement();
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM \"appUsers\".\"appUsers\"");

//            preparedStatement.setLong(1, userId);
//            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                return resultSet.getInt("count");
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
        return 0;
    }
}

