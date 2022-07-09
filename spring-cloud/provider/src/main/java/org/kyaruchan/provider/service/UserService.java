package org.kyaruchan.provider.service;
import org.kyaruchan.model.bean.User;
import java.util.List;

public interface UserService {
    boolean appendUser(User user);
    User getUser(Long id);
    List<User> getUserInDatabase(String dbName);
    List<User> getAll();
}
