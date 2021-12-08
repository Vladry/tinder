package tinder.dao;

import java.util.List;

public interface UserDao {
    boolean create(User user);
    User read(Long id);
    void update(User user);
    boolean delete(long id);
    List<User> findAll();
    User findByLoginPass(String login,String password);
}
