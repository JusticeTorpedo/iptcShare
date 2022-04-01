package cn.edu.ncut.service;

import cn.edu.ncut.domain.User;

public interface UserService {
    //用户注册
    Boolean userRegister(User user);

    //用户登录
    Boolean userLogin(String userName, String userPwd);

    //根据用户名查找用户id
    Integer getUserIdByUserName(String userName);

}
