package cn.edu.ncut.service;

import cn.edu.ncut.domain.UserProfile;

public interface UserProfileService {

    /**
     * 根据传入的userName返回user_profile的记录
     * @param userName
     * @return UserProfile
     */
    UserProfile getProfile(String userName);

    /**
     * 根据传入的userName创建一条记录
     * @param userName
     * @return
     */
    Integer initProfile(String userName);

    /**
     * 设置userName的avatar
     * @param avatarPath
     * @return
     */
    Integer setAvatar(String userName, String avatarPath);

    /**
     * 设置profile其他信息
     * @param profile
     * @return
     */
    Integer setProfile(UserProfile profile);
}
