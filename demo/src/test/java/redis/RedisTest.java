package redis;

import com.tal.DemoApplication;
import com.tal.peiyoupad.redis.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by SunGuiyong
 * on 2018/8/20.
 */
@RunWith(SpringRunner.class)

@SpringBootTest(classes = DemoApplication.class)
public class RedisTest {
    @Autowired
    private RedisTemplate<String, Object> template;

    @Test
    public void testDemo() {
//        User u = new User();
//        u.setUsername("abc");
//        template.opsForValue().set("u", u);
//
//        User u2 = (User) template.opsForValue().get("u");
//        System.out.println(u2.getUsername() + ".................");
//
//        template.opsForValue().set("name", "ywj");
//        System.out.println(template.opsForValue().get("name").toString());
    }

}
