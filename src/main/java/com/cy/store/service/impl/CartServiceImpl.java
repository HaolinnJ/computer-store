package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        if (result == null){
            Cart cart = new Cart();

            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);

            //获取product的价格
            Product product = productMapper.findById(pid);
            cart.setPrice(product.getPrice());

            cart.setCreatedUser(username);
            cart.setModifiedUser(username);
            cart.setCreatedTime(new Date());
            cart.setModifiedTime(new Date());

            Integer rows = cartMapper.insert(cart);

            if(rows!=1){
                throw new InsertException("Unknown problem has occurred during insert.");
            }

        } else {
            Integer rows = cartMapper.updateNumByCid(
                    result.getCid(),
                    amount + result.getNum(),
                    username,
                    new Date());
            if (rows!=1){
                throw new InsertException("Unknown problem has occurred during insert.");
            }
        }
    }
}
