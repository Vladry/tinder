package tinder.dao;

import java.util.List;

public interface LikedDao {
    boolean create(Long who_id,Long whom_id,boolean isLike);
    List<User> findLikedUsers(Long who_id);
    boolean findMark(Long who_id,Long whom_id);
    boolean update(Long who_id,Long whom_id,boolean isLike);
    boolean deleteMark(long who_id);
    boolean deleteAll();
    List<User> findAll(long who_id);
}
