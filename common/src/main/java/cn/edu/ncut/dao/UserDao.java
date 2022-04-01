package cn.edu.ncut.dao;

import cn.edu.ncut.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserDao {
    //向数据库的user表插入一条记录
    Integer insertUser(User user);

    //从数据库的user表按user_name字段查找记录
    User selectUserByName(@Param("userName") String name);

    //从数据库的user表按user_email字段查找记录
    User selectUserByEmail(@Param("userEmail") String email);
}
