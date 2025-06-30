package com.cy.store.service;

import com.cy.store.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// @SpringBootTest: 标注当前类是一个测试类，不会随同项目一块打包发送
@SpringBootTest
// @RunWith: 启动这个单元测试类（如果无则单元测试不能运行），需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)

public class CartServiceTests {
    @Autowired
    private ICartService cartService;
    /**单元测试类的方法:单独独立运行，不用启动整个项目，可以做单元测试
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须是void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须是public
     */

    @Test
    public void addToCart(){
        cartService.addToCart(11,10000001,5,"Administrator");
    }

    @Test
    public void delCartItem(){
        cartService.delCartItem(6,6);
    }

    @Test
    public void getVOByCids(){
        Integer[] cids = {2,3,13,14,15};
        List<CartVO> result = cartService.getVOByCids(cids,6);
        for(CartVO item:result){
            System.out.println(item);
        }
    }
}

