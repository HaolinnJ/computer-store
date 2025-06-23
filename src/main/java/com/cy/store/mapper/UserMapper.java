package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserMapper {
    /**
     * Insert User Data
     * @param user User Data
     * @return number of rows (add,delete,change,based on return value to decide if it is successfully inserted)
     */
    Integer insert(User user);

    /**
     * based on username to query user data
     * @param username
     * @return if the username is found, return the data of user, else return null
     */
    User findByUsername(String username);

    /**
     * based on the uid of user to change user password
     * @param uid uid of user
     * @param password new password
     * @param modifiedUser the user that modifies data
     * @param modifiedTime time
     * @return number of rows affected
     */
    Integer updatePassword(Integer uid,
                           String password,
                           String modifiedUser,
                           Date modifiedTime);

    /**
     * based on user uid to query user data
     * @param uid
     * @return if the uid is found, return the data of user, else return null
     */
    User findByUid (Integer uid);

    /**
     * update info of user
     * @param user
     * @return affected rows
     */
    Integer updateInfoByUid(User user);

    /**
     * change avatar based on uid
     * @Param("SQL映射文件中#{}占位符的变量名") 解决的问题：当SQL语句的占位符
     * 和映射接口方法参数名不一致时，需要将某个参数强行注入某个占位符变量时可以使用这个注解
     * @param uid ID
     * @param avatar 头像
     * @param modifiedUser
     * @param modifiedTime
     * @return affected rows
     */
    Integer updateAvatarByUid(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

}
