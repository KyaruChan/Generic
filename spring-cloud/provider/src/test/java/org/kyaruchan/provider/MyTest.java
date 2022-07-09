package org.kyaruchan.provider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kyaruchan.model.bean.User;
import org.kyaruchan.provider.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class MyTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper(){
        System.out.println(userMapper.appendUser(new User("Maruzen", "db1")));
        System.out.println(userMapper.queryAll());
        System.out.println(userMapper.queryUser(1L));
    }
}
