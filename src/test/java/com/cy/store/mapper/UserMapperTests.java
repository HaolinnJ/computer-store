package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

// @SpringBootTest: 标注当前类是一个测试类，不会随同项目一块打包发送
@SpringBootTest
// @RunWith: 启动这个单元测试类（如果无则单元测试不能运行），需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)

public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
    /**单元测试类的方法:单独独立运行，不用启动整个项目，可以做单元测试
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须是void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须是public
     */
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("tim");
        user.setPassword("1234");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("tim");
        System.out.println(user);
    }

    @Test
    public void findByUid(){
        User user = userMapper.findByUid(6);
        System.out.println(user);
    }

    @Test
    public void updatePassword(){
        Integer rows = userMapper.updatePassword(
                2,"321","Administrator", new Date()
        );
        System.out.println(rows);
    }

    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(6);
        user.setPhone("180190");
        user.setEmail("321@qq.com");
        user.setGender(1);
        Integer rows = userMapper.updateInfoByUid(user);
        System.out.println(rows);
    }

    @Test
    public void updateAvatarByUid(){
        Integer rows = userMapper.updateAvatarByUid(
                1,
                "test",
                "Administrator",
                new Date());
        System.out.println(rows);
    }

}
