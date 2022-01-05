package tinder.v_dao;

import tinder.dao.User;

import java.util.ArrayList;

public interface LikesDao_v {
    public void processLiked(int loggedInId, int candidateId, boolean verdict);
    public ArrayList<Integer> retrieveAllLiked(int loggedInId);
    public ArrayList<User> retrieveLikedUsers(int loggedInId);
}
