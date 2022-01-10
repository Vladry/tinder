package tinder.dao;

import tinder.domain.User;

public interface UserDao_v {
    int create(String email, String password, String name, int age, String urlPhoto);
    User retrieveByEmailPassword(String email, String password);
    User retrieveById(int id);
    User retrieveByIsLiked(int id);

}
