package org.kyaruchan.provider.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.kyaruchan.model.bean.User;
import java.util.List;

@Mapper
public interface UserMapper {
    boolean appendUser(User user);
    User queryUser(Long id);
    List<User> queryUserInDatabase(String dbName);
    List<User> queryAll();
}
