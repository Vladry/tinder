package tinder.v_dao;

import java.util.ArrayList;

public interface LikesDao_v {
    public void processLiked(int loggedInId, int candidateId, boolean verdict);
    public ArrayList<Integer> retrieveAllLiked(int loggedInId);
}
