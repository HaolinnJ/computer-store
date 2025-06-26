package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Insert;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**控制层类的基类*/
public class BaseController {
    /**操作成功的状态码*/
    public static final int OK = 200;
    // 请求处理方法，返回值就是需要传递给前端的数据
    // 自动将异常对象传递给此方法的参数列表上
    // 当项目中产生了异常，会被统一拦截到这个方法中，充当请求处理方法，返回值直接给到前端
    @ExceptionHandler({ServiceException.class, FileUploadException.class}) // 统一处理抛出的异常
    public JsonResult <Void> handleException(Throwable e){
        JsonResult <Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("Username already registered.");
        } else if (e instanceof UsernameNotFoundException){
            result.setState(4001);
            result.setMessage("User Data does not exist.");
        } else if (e instanceof PasswordNotMatchException){
            result.setState(4002);
            result.setMessage("Password not correct.");
        } else if (e instanceof AddressCountLimitException){
            result.setState(4003);
            result.setMessage("Address count exceeds limit.");
        } else if (e instanceof AddressNotFoundException){
            result.setState(4004);
            result.setMessage("Address does not exist.");
        } else if (e instanceof AccessDeniedException){
            result.setState(4005);
            result.setMessage("Invalid request.");
        } else if (e instanceof ProductNotFoundException){
            result.setState(4006);
            result.setMessage("Product does not exist.");
        } else if (e instanceof InsertException){
            result.setState(5000);
            result.setMessage("Registration failed.");
        } else if (e instanceof UpdateException){
            result.setState(5001);
            result.setMessage("Update failed.");
        } else if (e instanceof DeleteException){
            result.setState(5002);
            result.setMessage("Delete failed.");
        } else if (e instanceof FileEmptyException) {
            result.setState(6000);
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
        } else if (e instanceof FileStateException) {
            result.setState(6003);
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
        }
        return result;
    }

    /**
     * 获取session对象中的id
     * @param session
     * @return 当前登录的用户uid的值 (Integer)
     */
    protected final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid")
                .toString());
    }

    /**
     * 获取当前登录用户的username
     * @param session
     * @return 当前登录用户的用户名
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
