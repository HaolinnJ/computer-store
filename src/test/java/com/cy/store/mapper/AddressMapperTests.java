package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


// @SpringBootTest: 标注当前类是一个测试类，不会随同项目一块打包发送
@SpringBootTest
// @RunWith: 启动这个单元测试类（如果无则单元测试不能运行），需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)

public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;
    /**单元测试类的方法:单独独立运行，不用启动整个项目，可以做单元测试
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须是void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须是public
     */

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(6);
        address.setPhone("1234567");
        address.setName("myself");
        Integer rows = addressMapper.insert(address);
        System.out.println(rows);
    }

    @Test
    public void countByUid(){
        Integer rows = addressMapper.countByUid(6);
        System.out.println(rows);
    }

    @Test
    public void findByUid(){
        List<Address> list = addressMapper.findByUid(6);
        System.out.println(list);
    }

    @Test
    public void findByAid() {
        System.err.println(addressMapper.findByAid(3));
    }

    @Test
    public void updateNonDefault() {
        System.out.println(addressMapper.updateNonDefault(6));//有几条数据影响行数就输出几
    }

    @Test
    public void updateDefaultByAid() {
        addressMapper.updateDefaultByAid(7,"Administrator",new Date());
    }

    @Test
    public void deleteByAid() {
        addressMapper.deleteByAid(1);
    }

    @Test
    public void findLastModified() {
        System.out.println(addressMapper.findLastModified(6));
    }
}

