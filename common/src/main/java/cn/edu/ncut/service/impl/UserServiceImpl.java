package cn.edu.ncut.service.impl;

import cn.edu.ncut.dao.UserDao;
import cn.edu.ncut.domain.User;
import cn.edu.ncut.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    /*
    用户注册
     */
    @Override
    public Boolean userRegister(User user) {
        Boolean result = false;

        //检查用户名是否重复
        if(userDao.selectUserByName(user.getUserName())==null){
            //检查邮箱是否重复
            if(userDao.selectUserByEmail(user.getUserEmail())==null){
                if(userDao.insertUser(user) == 1){
                    result = true;
                }
            }
        }
        return result;
    }

    /*
    登录验证
     */
    @Override
    public Boolean userLogin(String userName,String userPwd){
        System.out.println("Service: "+userName+","+userPwd);

        Boolean result = false;

        //尝试按用户名搜索
        User temp = userDao.selectUserByName(userName);

        //若用户名搜索无结果，则尝试按邮箱搜索
        if(temp == null){
            temp = userDao.selectUserByEmail(userName);
        }

        //若搜索成功，则判断密码是否正确
        if(temp != null){
            if(userPwd.equals(temp.getUserPwd())){
                result = true;
            }
        }

        return result;
    }

    /**
     * @param userName
     * @return userId
     */
    @Override
    public Integer getUserIdByUserName(String userName) {
        User user = userDao.selectUserByName(userName);
        if (user != null){
            return user.getUserId();
        }
        return -1;
    }
}
