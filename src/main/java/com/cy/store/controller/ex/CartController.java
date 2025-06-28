package com.cy.store.controller.ex;

import com.cy.store.controller.BaseController;
import com.cy.store.service.ICartService;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carts")

public class CartController extends BaseController {
    @Autowired
    ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult <Void> addToCart(Integer pid, Integer amount, HttpSession session){
        cartService.addToCart(
                getUidFromSession(session),
                pid,
                amount,
                getUsernameFromSession(session)
        );

        return new JsonResult <Void> (OK);
    }

}
