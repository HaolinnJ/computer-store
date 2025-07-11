package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.ex.AccessDeniedException;
import com.cy.store.service.ex.CartNotFoundException;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null){
            throw new CartNotFoundException("Product in cart not found.");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("Invalid request.");
        }

        Integer num = result.getNum()+1;
        Integer rows = cartMapper.updateNumByCid(cid, num,username,new Date());
        if (rows!=1){
            throw new UpdateException("Unknown problem has occurred during update.");
        }
        return num;
    }

    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null){
            throw new CartNotFoundException("Product in cart not found.");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("Invalid request.");
        }

        Integer num = result.getNum()-1;
        if(num==0){
            cartMapper.deleteByCid(cid);
            return null;
        }
        Integer rows = cartMapper.updateNumByCid(cid, num,username,new Date());
        if (rows!=1){
            throw new UpdateException("Unknown problem has occurred during update.");
        }
        return num;
    }

    @Override
    public void delCartItem(Integer cid, Integer uid) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null){
            throw new CartNotFoundException("Product in cart not found.");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("Invalid request.");
        }
        Integer rows = cartMapper.deleteByCid(cid);
        if (rows!=1){
            throw new UpdateException("Unknown problem has occurred during delete.");
        }
    }

    @Override
    public List<CartVO> getVOByCids(Integer[] cids, Integer uid) {
        List<CartVO> list = cartMapper.findVOByCids(cids);
        // 迭代器遍历：
        Iterator<CartVO> it = list.iterator();
        while(it.hasNext()){
            CartVO cart = it.next();

            if(!cart.getUid().equals(uid)){
                it.remove();
            }
        }
        return list;
    }
}
