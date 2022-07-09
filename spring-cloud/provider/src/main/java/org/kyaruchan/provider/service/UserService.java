package org.kyaruchan.provider.service;
import org.kyaruchan.model.bean.User;
import java.util.List;

public interface UserService {
    User getUser(Long id);
    List<User> getUserInDatabase(String dbName);
    List<User> getAll();
}
