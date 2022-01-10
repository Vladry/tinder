package tinder.dao;

import tinder.domain.User;

import java.util.ArrayList;

public interface LikesDao_v {
    public void processLiked(int loggedInId, int candidateId, boolean verdict);
    public ArrayList<Integer> retrieveAllLiked(int loggedInId);
    public ArrayList<User> retrieveLikedUsers(int loggedInId);
}
