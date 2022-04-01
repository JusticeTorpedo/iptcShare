package cn.edu.ncut.service;

public interface AdminService {
    //根据传入的userId判断是否是管理员
    Boolean isAdmin(Integer userId);
}
