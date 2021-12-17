package tinder.dao;

import org.postgresql.ds.PGPoolingDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LikedJdbcDao implements LikedDao {

    private PGPoolingDataSource source;

    public LikedJdbcDao() {
        source = new PGPoolingDataSource();
        source.setServerName("ec2-52-86-177-34.compute-1.amazonaws.com");
        source.setDatabaseName("d7g10jrgsjruk4");
        source.setUser("mtmaprkfztrfne");
        source.setPassword("d727d367387272970efb9ca62ff523bb77695ebf5f9a7e7b83af48e216e2fb64");
        source.setMaxConnections(10);
    }

    @Override
    public boolean create(Long who_id, Long whom_id, boolean isLike) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO \"appUsers\".\"likedUsers\"(who_id,whom_id,is_liked) VALUES (?,?,?)");

            preparedStatement.setLong(1, who_id);
            preparedStatement.setLong(2, whom_id);
            preparedStatement.setBoolean(3, isLike);

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
    public List<User> findLikedUsers(Long who_id) {
        return null;
    }

    @Override
    public boolean findMark(Long who_id, Long whom_id) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"appUsers\".\"likedUsers\" WHERE who_id=? AND whom_id=?");
            preparedStatement.setLong(1, who_id);
            preparedStatement.setLong(2, whom_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("who_id");
//                System.out.println(id);
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
    public boolean update(Long who_id,Long whom_id,boolean isLike) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update \"appUsers\".\"likedUsers\"\n" +
                            "set is_liked = ?\n" +
                            "where who_id = ? AND whom_id = ?");
            preparedStatement.setBoolean(1, isLike);
            preparedStatement.setLong(2, who_id);
            preparedStatement.setLong(3, whom_id);

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
    public boolean deleteMark(long who_id) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM \"likedUsers\".\"appUsers\" WHERE id=?");
            preparedStatement.setLong(1, who_id);

            int executionResult = preparedStatement.executeUpdate();

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
    public boolean deleteAll() {
        Connection connection = null;
        try {
            connection = source.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM \"appUsers\".\"likedUsers\"");
            int executionResult = preparedStatement.executeUpdate();
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
    public List<User> findAll(long who_id) {
        return null;
    }
}
