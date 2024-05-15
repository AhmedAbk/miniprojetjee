package DAO;

import java.util.List;
import beans.User;

public interface userInterface {
    List<User> getAll();
    void addUser(User user);
    User findUser(int id);
    boolean deleteUser(int id);
    boolean updateUser(int userId, String name, String prenom, String email, String password);
    public int getTotalUserCount();
    
}
