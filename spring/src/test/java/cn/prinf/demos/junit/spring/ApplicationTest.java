package cn.prinf.demos.junit.spring;

import cn.prinf.demos.junit.spring.entity.User;
import cn.prinf.demos.junit.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 注意这里用的是内存数据库
 * 跑这个类  会自动跑两个用例，先 should_add_user 再 should_list_users。 顺序是按照名称排序的。should_list_users这个会查到一条插入的
 * 单独跑 should_list_users 查询到的是0条记录
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {Application.class},
        value = {"server.port=9090"},
        properties = {"server.port=9090"}
)
public class ApplicationTest {

    @Autowired
    private UserService userService;

    @Test
    public void should_list_users() {
        List<User> users = userService.listAll();
        System.out.println(users);
    }

    @Test
    public void should_add_user() {
        User user = new User() {{
            setUsername("zhangsan");
            setPassword("123456");
        }};

        userService.add(user);
    }
}
