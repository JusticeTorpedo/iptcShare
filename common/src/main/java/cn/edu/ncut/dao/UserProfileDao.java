package cn.edu.ncut.dao;

import cn.edu.ncut.domain.UserProfile;
import org.apache.ibatis.annotations.Param;

public interface UserProfileDao {
    //根据username查找记录
    UserProfile selectProfileByUserName(@Param("userName") String userName);

    //向user_profile中插入一条记录，仅user_name字段有值
    Integer insertUserNameIntoProfile(@Param("userName") String userName);

    //向user_profile中，user_name的所在行，写入user_avatar
    Integer updateAvatarIntoProfile(@Param("userName") String userName, @Param("avatarPath") String avatarPath);

    //向user_profile中，user_name所在的行，写入其他数据
    Integer updateProfile(UserProfile userProfile);
}
