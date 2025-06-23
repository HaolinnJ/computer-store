package com.cy.store.service;

import com.cy.store.entity.User;

/** 用户模块业务层接口*/
public interface IUserService {
    /**
     * user register method
     * @param user
     */
    void reg(User user);

    /**
     * login service
     * @param username
     * @param password
     * @return matched user data, if no match then return null
     */
    User login(String username, String password);

    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

    /**
     * based on uid to query user data
     * @return
     */
    User getByUid(Integer uid);

    /**
     * uid通过控制层在session中获取然后传递给业务层，并在业务层封装到User对象中
     * @param uid
     * @param user
     */
    void changeInfo(Integer uid, String username, User user);

    /**
     * change user avatar
     * @param uid
     * @param avatar
     * @param username
     */
    void changeAvatar(Integer uid, String avatar, String username);
}
