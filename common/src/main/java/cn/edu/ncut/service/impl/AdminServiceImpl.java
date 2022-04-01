package cn.edu.ncut.service.impl;

import cn.edu.ncut.dao.AdminDao;
import cn.edu.ncut.domain.Admin;
import cn.edu.ncut.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    @Override
    public Boolean isAdmin(Integer userId) {
        Admin admin = adminDao.selectAdminByUserId(userId);
        if(admin == null){
            return false;
        }
        return true;
    }
}
