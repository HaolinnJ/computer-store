package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** Controller中的方法向后端传递前端的值，return给前端后端的数据*/

@RestController // @Controller + @ResponseBody
@RequestMapping("users")

public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    /**请求处理方法的参数列表设置为pojo类型来接受前端数据
     * SpringBoot将前端的url地址中的参数名和pojo类的属性名进行比较
     * 如果两个名称相同，则将值注入到pojo类的对应属性上*/
    @RequestMapping("reg")
    public JsonResult <Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(OK);
    }
    /*    @RequestMapping("reg")
    public JsonResult <Void> reg(User user){
        // 创建响应结果对象
        JsonResult <Void> result = new JsonResult<>();
        try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("Registration successful.");
        } catch (UsernameDuplicatedException e) {
            result.setState(4000);
            result.setMessage("Username already registered.");
        } catch (InsertException e) {
            result.setState(5000);
            result.setMessage("Registration failed.");
        }
        return result;
    }*/
    //Test:访问 http://localhost:8080/users/reg?username=bim&password=123456 输出一个json格式

    /**请求处理方法的参数列表设置为非pojo类型来接受前端数据
     * SpringBoot直接将请求的参数名和方法的参数名直接进行比较
     * 如果两个名称相同则自动完成值的注入*/
    @RequestMapping("login")
    public JsonResult <User> login(String username,
                                   String password,
                                   HttpSession session)
    //在这里声明了HttpSession这个变量，SpringBoot自动将全局变量session对象注入到这个请求处理方法的session形参上
    //因此调用该方法的时候不需要输入session变量的值
    {   User data = userService.login(username,password);
        // 向session对象中完成数据的绑定（session全局的）
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username",data.getUsername());
        // 获取session中绑定的数据
        System.out.println(getUidFromSession(session));
        System.out.println(getUsernameFromSession(session));

        return new JsonResult<User>(OK, data);
    }
    //Test:访问 http://localhost:8080/users/login?username=dim&password=123456

    @RequestMapping("change_password")
    public JsonResult <Void> changePassword(String oldPassword,
                                            String newPassword,
                                            HttpSession session){
        Integer uid = getUidFromSession(session);
        String modifiedUser = getUsernameFromSession(session);
        userService.changePassword(uid, modifiedUser,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult <User> getByUid (HttpSession session){
        User data = userService.getByUid(getUidFromSession(session));
        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("change_info")
    public JsonResult <Void> changeInfo (User user, HttpSession session){
        userService.changeInfo(getUidFromSession(session),getUsernameFromSession(session),user);
        return new JsonResult<>(OK);
    }

    /**设置上传文件的最大值*/
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;

    /**限制上传文件的类型*/
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static{
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    @RequestMapping("change_avatar")
    public JsonResult <String> changeAvatar(HttpSession session, MultipartFile file){
        // 判断文件是否为空
        if (file.isEmpty()){
            throw new FileEmptyException("File is empty.");
        }
        // 文件大小检测
        if (file.getSize() > AVATAR_MAX_SIZE){
            throw new FileSizeException("File size is too large.");
        }
        // 文件类型检测
        String contentType = file.getContentType();
        // contains:如果集合包含某个元素则返回true
        if (!AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException("File type is not supported.");
        }
        //上传的文件.../upload/photo.png
        String parent =
                session.getServletContext().getRealPath("/upload"); //session.getServletContext()获取当前Web应用程序的上下文
        // File对象指向这个路径，File是否存在
        File dir = new File(parent);
        if (!dir.exists()){ //检测目录是否存在
            dir.mkdirs(); // 如果不存在则创建目录
        }
        // 获取文件名称，使用UUID工具来生成一个新的字符串作为文件名
        // 例如avatar01.png
        String originalFilename = file.getOriginalFilename();
        System.out.println("OriginalFilename = " + originalFilename);
        // 截取文件后缀
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        // 用UUID随机生成一个文件名并和后缀拼接在一起
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;

        // 在dir目录下创建一个空的文件名为filename的文件
        File dest = new File(dir, filename);
        // 参数file中的数据写入到这个空文件中
        try {
            file.transferTo(dest);
        } catch (FileStateException e) {
            throw new FileStateException("File state error");
        }
        catch (IOException e) {
            throw new FileUploadIOException("File upload IO error.");
        }

        String avatar = "/upload/"+filename;
        userService.changeAvatar(getUidFromSession(session),avatar,getUsernameFromSession(session));

        //返回用户头像的路径给前端页面，用于将来头像的展示使用
        return new  JsonResult<String> (OK, avatar);
    }
}
