package cn.edu.ncut.dao;

import cn.edu.ncut.domain.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {
    //从Admin表中根据userId查找记录
    Admin selectAdminByUserId(@Param("userId") Integer userId);

}
