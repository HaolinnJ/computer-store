package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;
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

public class CartMapperTests {
    @Autowired
    private CartMapper cartMapper;
    /**单元测试类的方法:单独独立运行，不用启动整个项目，可以做单元测试
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须是void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须是public
     */
    @Test
    public void insert(){
        Cart cart = new Cart();
        cart.setUid(11);
        cart.setPid(10000001);
        cart.setNum(3);
        cart.setPrice(4L);//长整型
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid(){
        cartMapper.updateNumByCid(1,2,"Administrator",new Date());
    }

    @Test
    public void findByUidAndPid(){
        System.out.println(cartMapper.findByUidAndPid(11,10000001));
    }

    @Test
    public void findVOByUid(){
        System.out.println(cartMapper.findVOByUid(6));
    }

    @Test
    public void findByCid(){
        cartMapper.findByCid(4);
    }

    @Test
    public void deleteByCid(){
        cartMapper.deleteByCid(4);
    }

    @Test
    public void findVOByCids(){
        Integer[] cids = {1,2,3,4,13,14};
        List<CartVO> list = cartMapper.findVOByCids(cids);
        for(CartVO item :list){
            System.out.println(item);
        }
    }
}

