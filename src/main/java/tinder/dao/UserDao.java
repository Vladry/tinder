package tinder.dao;

import java.util.List;

public interface UserDao {
    boolean create(User user);
    User read(Long id);
    boolean update(User user);
    boolean delete(long id);
    User findByLoginPass(String login,String password);
    List<User> findAll();
    int findNumRaws();
}
