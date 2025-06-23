package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/** 用户模块业务层的实现类*/
@Service //@Service:将当前类的对象交给Spring来管理，自动创建对象以及对象的维护
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        // get username from user
        String username = user.getUsername();
        // use findByUsername(username) to check if username is already registered
        User result = userMapper.findByUsername(username);
        // check if result is null, if not null then throw UsernameDuplicatedException
        if (result != null) {
            throw new UsernameDuplicatedException("Username already registered.");
        }

        //is_delete
        user.setIsDelete(0);
        //log information
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        // password: MD5 algorithm (salt + password + salt)
        String oldPassword = user.getPassword();
        // get salt (randomly generated)
        String salt = UUID.randomUUID().toString().toUpperCase();
        // combine salt with password
        String md5Password = getMD5Password(oldPassword,salt);
        // set user password
        user.setPassword(md5Password);
        // save salt
        user.setSalt(salt);

        // register service (rows == 1 if successful)
        Integer rows = userMapper.insert(user);
        if (rows != 1){
            throw new InsertException("Registration failed.");
        }
    }

    @Override
    public User login(String username, String password) {
        // query if user exists
        User result = userMapper.findByUsername(username);
        if (result == null){
            throw new UsernameNotFoundException("User data does not exist.");
        }
        // check if the account is deleted
        if (result.getIsDelete()==1){
            throw new UsernameNotFoundException("User data does not exist.");
        }
        // 检测密码是否匹配
        //1.先获取数据库加密后的密码
        String oldPassword = result.getPassword();
        //2.和用户传递过来的密码进行比较
        //2.1 先获取盐值salt
        String salt = result.getSalt();
        //2.2 将用户密码按照相同的MD5算法进行加密
        String newMd5Password = getMD5Password(password,salt);
        //3. 将密码进行比较
        if (!newMd5Password.equals(oldPassword)){
            throw new PasswordNotMatchException("Password not correct.");
        }

        // return only useful information
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() ==1){
            throw new UsernameNotFoundException("User data does not exist.");
        }
        // compare old password with entered password
        String oldMd5Password = getMD5Password(oldPassword,result.getSalt());
        if (!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("Passwords do not match.");
        }
        // update new password
        String newMd5Password = getMD5Password(newPassword,result.getSalt());
        Integer rows = userMapper.updatePassword(uid,newMd5Password,username,new Date());
        if (rows != 1){
            throw new UpdateException("Unknown problem has occurred.");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete()==1){
            throw new UsernameNotFoundException("User data does not exist.");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());

        return user;
    }

    @Override
    public void changeInfo(Integer uid,String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1){
            throw new UsernameNotFoundException("User data does not exist.");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1){
            throw new UpdateException("Unknown problem has occurred during update.");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1){
            throw new UsernameNotFoundException("User data does not exist.");
        }

        Integer rows = userMapper.updateAvatarByUid(uid, avatar,username, new Date());
        if(rows != 1){
            throw new UpdateException("Unknown problem has occurred during update");
        }
    }

    private String getMD5Password (String password, String salt){
        // md5 (for 3 times)
        for(int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }

}
