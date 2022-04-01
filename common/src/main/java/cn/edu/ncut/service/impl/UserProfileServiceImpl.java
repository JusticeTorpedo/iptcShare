package cn.edu.ncut.service.impl;

import cn.edu.ncut.dao.UserProfileDao;
import cn.edu.ncut.domain.UserProfile;
import cn.edu.ncut.service.UserProfileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Resource
    private UserProfileDao profileDao;

    @Override
    public UserProfile getProfile(String userName) {
        return profileDao.selectProfileByUserName(userName);
    }

    @Override
    public Integer initProfile(String userName) {
        return profileDao.insertUserNameIntoProfile(userName);
    }

    @Override
    public Integer setAvatar(String userName, String avatarPath) {
        return profileDao.updateAvatarIntoProfile(userName, avatarPath);
    }

    @Override
    public Integer setProfile(UserProfile profile) {
        return profileDao.updateProfile(profile);
    }
}
